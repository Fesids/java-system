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
    const {getClientRequestDetail, getRequestDetail, updateDestIdAndSenderId, getDepartmentList,currentUser, getUserProfileByUserId, createConversation, getFriendByProfileId} = useContext(AppContext);

    //const [clientRequest, setClientRequest] = useState({} as IRequestClient);
    const [request, setRequest] = useState({} as IRequest);
    const [departments, setDepartments] = useState([] as IDepartment[]);
    const [currentUserProfile, setCurrentUserProfile] = useState({} as IProfile);
    const [requestUserProfile, setRequestUserProfile] = useState({} as IProfile);
    const [requestFriend, setRequestFriend] = useState({} as IFriend);

    const [showMore, setShowMore] = useState(false);

    useEffect(()=>{
        let cr_id = 0;
        if(id){
            cr_id = parseInt(id);
        };

        getRequestDetail(cr_id).then(resp => setRequest(resp));
    });

    // set departments
    useEffect(()=>{
        getDepartmentList().then(resp => setDepartments(resp));
    })

    // CURRENT USER PROFILE
    useEffect(()=>{
        let cc = 0;
        if(currentUser?.user_id){
            cc = currentUser.user_id
        }

        getUserProfileByUserId(cc).then(resp => setCurrentUserProfile(resp));
    });

    // CURRENT REQUEST USER PROFILE AND FRIEND
    useEffect(()=>{
        let cc = request?.user_sender_id;
        getUserProfileByUserId(cc).then(resp => setRequestUserProfile(resp));

        if(requestUserProfile.id){
            getFriendByProfileId(requestUserProfile.id).then(resp => setRequestFriend(resp));
        }

    })

   
    /*console.log(currentUserProfile);
    console.log(requestUserProfile);
    console.log(requestFriend)*/
    console.log(request)
    


    const handleCreateChat = (e:any) =>{
        e.preventDefault()
        createConversation(currentUserProfile.id, requestFriend.id)
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