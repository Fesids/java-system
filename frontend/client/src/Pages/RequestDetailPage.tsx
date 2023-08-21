import { useContext, useEffect, useState } from "react"
import { useParams } from "react-router"
import { AppContext } from "../Context/AppContext";
import { IRequestClient } from "../Interfaces/RequestClient";
import { IRequest } from "../Interfaces/Request";
import { IDepartment } from "../Interfaces/Department";
import { IProfile } from "../Interfaces/Profile";
import { IFriend } from "../Interfaces/Friend";


export const RequesDetailPage = () =>{

    const {id, dept_name} = useParams();
    const {getClientRequestDetail, getRequestDetail, updateDestIdAndSenderId, getDepartmentList,currentUser, getUserProfileByUserId, createConversation, getFriendByProfileId, createChat, currentUserProfile} = useContext(AppContext);

    //const [clientRequest, setClientRequest] = useState({} as IRequestClient);
    const [request, setRequest] = useState({} as IRequest);
    const [departments, setDepartments] = useState([] as IDepartment[]);
    //const [currentUserProfile, setCurrentUserProfile] = useState({} as IProfile);
    const [requestUserProfile, setRequestUserProfile] = useState({} as IProfile);
    

    const [showMore, setShowMore] = useState(false);

    useEffect(()=>{
        let cr_id = 0;
        if(id){
            cr_id = parseInt(id);
        };

        getRequestDetail(cr_id).then(resp => setRequest(resp));
    });

    useEffect(()=>{
        let reqUserId = 0;
        if(request){
            reqUserId = request.user_sender_id;
            
        }
        getUserProfileByUserId(reqUserId).then(resp => setRequestUserProfile(resp));
    })

    // set departments
    useEffect(()=>{
        getDepartmentList().then(resp => setDepartments(resp));
    })

    
    
    console.log(currentUserProfile)
    
    const handleCreateChat = async (e:any) =>{
        e.preventDefault();
        let currentUserProfileId = 0;
        if(currentUserProfile){
            currentUserProfileId = currentUserProfile.id
        }
        createChat(currentUserProfileId, requestUserProfile.id);

    }

    

    const testeWindow = () =>{
        //window.open("teste.html", "", h)
    }

    let body = ""
    if(request.body){
        body = request.body
    }
    

    return(
        <div className="request-detail-container">
            <div className="request-detail">
            
               
                <p className="subject">subject {request.subject}</p>

                <div className="request-body">
                    {showMore?body: body.substring(0, 100)} <p onClick={()=> setShowMore(!showMore)}>{!showMore?"show more":"show less"}</p>

                </div>


            
                <p>created at {request.created_at}</p>

                <div className="buttons-container">
                    <button onClick={(e)=> handleCreateChat(e)} className="connection-btn">+ Send message to {requestUserProfile.name} </button>
                    <button className="btn btn-outline-danger">reject</button>
                </div>
            </div>
        </div>

        
    )
}