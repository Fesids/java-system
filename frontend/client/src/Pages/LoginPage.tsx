import { useContext, useEffect, useState } from "react"
import { IUser, LoginResponseData, UserLoginReq } from "../Interfaces/User"
import { AppContext } from "../Context/AppContext";
import Cookie from "js-cookie";
import { useNavigate } from "react-router";
import axios from "axios";
import { any } from "prop-types";

export const LoginPage = () =>{

    const [userLogin, setUserLogin] = useState({} as UserLoginReq);
    const [userBody, setUserbody] = useState({} as LoginResponseData);

    const {login} = useContext(AppContext);

    const [teste, setTeste] = useState();

    const navigate = useNavigate();

    useState(()=>{
        const funcao = async () =>{
            try{
                const resp = await axios.get("http://localhost:8083/api/auth/testezin");
                return resp.data
            }catch(err){
                throw new Error("merda")
            }
        }
        
        console.log(funcao())
    })

    const handleChange = (e:any)=>{
        setUserLogin({...userLogin, [e.target.name]: e.target.value});
    }

    const LoginHandle = (e:any) =>{
        e.preventDefault()
        const login_body = {
            username: userLogin.email,
            password: userLogin.password
        }
        /*login(e, login_body).then(
            resp => {setUserbody(resp); /*navigate("../", {replace:true})}
        );*/
        login(e, login_body)
        .then(resp => setUserbody(resp));


    }

    useEffect(()=>{
        if(userBody.token){
            Cookie.set("auth_cookie", userBody.token);
        }
    })

    console.log(userBody);
    
    return(
        <div className="auth-container">
            <div className="create-user-form">
                <div className="auth-header">
                    <h2 className="mt-2 mb-2">Login</h2>
                    <p>Enter your credentials</p>
                </div>
               
                <form method="post" onSubmit={(e) => LoginHandle(e)} >
                    

                    <div className="form-group">
                        <label htmlFor="email" className="form-label mt-3">Email : </label>
                        <input name="email" className="form-control" id="email" onChange={(e)=> handleChange(e)}></input>
                    </div>

                    <div className="form-group">
                        <label htmlFor="password" className="form-label mt-3">Password : </label>
                        <input name="password" className="form-control" id="password" onChange={(e)=> handleChange(e)}></input>
                    </div>

                    <input type="submit" value={"login"} className="btn-submit-auth"></input>
                </form>
            </div>

        </div>
        
    )
}