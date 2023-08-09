import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router"
import { AppContext } from "../../Context/AppContext";
import { IProfile } from "../../Interfaces/Profile";
import { IMessage } from "../../Interfaces/Message";
import { MessageList } from "../../Components/ChatBoxDetail";

export const ChatDetailPage = () =>{

    const [profileDetail, setProfileDetail] = useState({} as IProfile);
    const [currentUserProfile, setCurrentUserProfile] = useState({} as IProfile);
    const [sentMessages, setSentMessages] = useState([] as Array<IMessage>);
    const [receivedMessages, setReceivedMessages] = useState([] as Array<IMessage>);
    const [text, setText] = useState("");
    
    const {profileId} = useParams();
    const {getProfileDetail, getUserProfileByUserId, currentUser, getSentMessages, getReceivedMessages, sentMessage} = useContext(AppContext);


    const handleSetMsgBody = (e:any)=>{
        setText(e.target.value)
    }

    const handleSent = (e:any) =>{
        e.preventDefault();
        const msg_body = {
            msg_sender: currentUserProfile?.id,
            msg_receiver: profileDetail?.id,
            body: text
        }
        sentMessage(msg_body);
    }


    // SET CURRENT USER PROFILe
    useEffect(()=>{
        let current_user_id = 0;
        if(currentUser?.user_id){
            current_user_id = currentUser.user_id
        }
        getUserProfileByUserId(current_user_id).then(resp => setCurrentUserProfile(resp))
    }, [])



    // SET PROFILE DETAIL
    useEffect(()=>{
        let prof_id = 0;
        if(profileId){
            prof_id = parseInt(profileId)
        }


        getProfileDetail(prof_id).then(resp => setProfileDetail(resp))
    }, []);

    //SET SENT MESSAGES
    useEffect(()=>{

        getSentMessages(currentUserProfile?.id, profileDetail?.id).then(resp => setSentMessages(resp));
    });

    //SET RECEIVED MESSAGES
    useEffect(()=>{
        getReceivedMessages(currentUserProfile?.id, profileDetail?.id).then(resp => setReceivedMessages(resp));
    })


    console.log(sentMessages);
    //console.log(receivedMessages);
/*


    console.log(profileDetail);
    console.log(currentUserProfile)*/
    //console.log(profileDetail?.id);
    console.log(currentUserProfile?.id)

    console.log(text)


    return(
        <div className="chat-container2">
             <div className="identity">
                <div>
                
                    <a href="{% url 'index' %}">
                        <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="26"
                        height="26"
                        fill="currentColor"
                        className="bi bi-house"
                        viewBox="0 0 16 16"
                    >
                        <path
                        fill-rule="evenodd"
                        d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"
                        />
                        <path
                        fill-rule="evenodd"
                        d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"
                        />
                        </svg>
                    </a>
                </div>

                <h3>{profileDetail.name}</h3>


                <div className="pro-pic">
                    <img src={profileDetail.pic} alt="profile-picture"/>
                </div>


            </div>


            <div className="chat-messages-container">
                
                  <MessageList receivedList={receivedMessages} senderId={currentUserProfile?.id} sentList={sentMessages}/>  
            </div>

            <div className="chat-message-send-container">
                <form method="post" className="my-form" onSubmit={(e:any)=> handleSent(e)}>
            
                    <textarea className="forms" rows={3} placeholder="Type a message" name="text" onChange={(e)=> handleSetMsgBody(e)}>


                    </textarea>

                    <input type="submit" value="send" className="btn-input" name="send"/>
                </form>
            </div>

        </div>
    )
}