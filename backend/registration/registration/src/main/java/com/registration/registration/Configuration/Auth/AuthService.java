package com.registration.registration.Configuration.Auth;


import com.registration.registration.Configuration.JwtService;
import com.registration.registration.DTO.UserRequest;
import com.registration.registration.models.User;
import com.registration.registration.repositories.UserRepository;
import com.registration.registration.services.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetail;

    @Autowired
    private UserService userService;


    public User register(UserRequest userRequest){
        User user = userService.createExternal(userRequest);
        String jwt = jwtService.generateToken(user);

        return user;//Response.builder().token(user).build();
    }

    public User registerStaff(UserRequest userRequest, String role){
        User user = userService.createStaff(userRequest, role);
        String jwt = jwtService.generateToken(user);

        return user;//Response.builder().token(jwt).build();
    }

    public LoginResponse login(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        var user = this.userDetail.loadUserByUsername(authRequest.username());
        var getLoginUser = this.userService.getUserByEmail(authRequest.username()).get();
        String jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder().username(getLoginUser.getUsername())
                .email(getLoginUser.getEmail())
                .token(jwtToken)
                .department_id(0)
                .user_id(getLoginUser.getUser_id())
                .build();

    }



}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Response{
    private String token;
}


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class LoginResponse{
    private Long user_id;
    private String token;
    private String email;
    private String username;
    private int department_id;
}










