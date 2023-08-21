package com.registration.registration.Controllers;

import com.registration.registration.Configuration.Auth.AuthRequest;
import com.registration.registration.Configuration.Auth.AuthService;
import com.registration.registration.Configuration.JwtService;
import com.registration.registration.DTO.FriendRequest;
import com.registration.registration.DTO.ProfileRequest;
import com.registration.registration.DTO.UserRequest;
import com.registration.registration.Template.RestTemplateClass;
import com.registration.registration.UTILS.GeneralUtilities;
import com.registration.registration.models.Friend;
import com.registration.registration.models.Profile;
import com.registration.registration.models.User;
import com.registration.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class registrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplateClass template;

    @Autowired
    //private WebClientConfig webClient;
    private WebClient.Builder webClient;

   @GetMapping("/all")
   public ResponseEntity<List<User>> getAllUsers(){
       return ResponseEntity.ok().body(userService.getAllUsers().stream().toList());
   }

    @PostMapping("/new/external")
    public ResponseEntity<?> registerExternalUser(@RequestBody UserRequest userRequest){

        try {


            URI uri = GeneralUtilities.toUri("/new/");

            var newUser = authService.register(userRequest);

            // PROFILE REQUEST
            Set<?> friends;
            friends = new HashSet<>();
            var pic = " ";
            var profile_body = new ProfileRequest(newUser.getUser_id(), newUser.getUsername(), pic, friends);
            var result = webClient.build().post()
                    .uri("http://localhost:8088/api/profile/new")
                    .body(Mono.just(profile_body), ProfileRequest.class)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();




            return ResponseEntity.ok().body(newUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping("/new/staff/{role}")
    public ResponseEntity<?> registerStaff(@RequestBody UserRequest userRequest, @PathVariable("role") String role){
        try{
            URI uri = GeneralUtilities.toUri("/new/");
            var newUser = authService.registerStaff(userRequest, role);


            // PROFILE REQUEST
            Set<?> friends;
            friends = new HashSet<>();
            var pic = " ";
            var profile_body = new ProfileRequest(newUser.getUser_id(), newUser.getUsername(), pic, friends);
            var result = webClient.build().post()
                    .uri("http://localhost:8088/api/profile/new")
                    .body(Mono.just(profile_body), ProfileRequest.class)
                    .retrieve()
                    .bodyToMono(Profile.class)
                    .block();

            assert result != null;
            var friend_body = new FriendRequest(result.getId());
            webClient.build().post()
                    .uri("http://localhost:8088/api/friend/new")
                    .body(Mono.just(friend_body), FriendRequest.class)
                    .retrieve()
                    .bodyToMono(Friend.class)
                    .block();

            return ResponseEntity.ok().body(newUser);
        } catch (Exception e){
            return ResponseEntity.ok().body("Something went wrong");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest){
        try{
            var authRes = authService.login(authRequest);
            return ResponseEntity.ok().body(authRes);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to login");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        jwtService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/getToken")
    public String getToken(@RequestBody String email){
        var user = userService.getUserByEmail(email).get();
        return jwtService.generateToken(user);
    }

    @GetMapping("/search/department/{dept_id}")
    public ResponseEntity<?> getUsersByDept(@PathVariable("dept_id") String id){
        /*var department_exist = webClient.webClienrBuilder().build().get()
                .uri("http://department-service/api/department/detail/"+id)
                .retrieve()
                .bodyToMono(DetResponse.class)
                .block();
        if(department_exist ==  null){
            return ResponseEntity.ok().body("department with id "+id+" doesn't exist");
        }*/

        return ResponseEntity.ok().body(/*department_exist*/null);
    }


}
