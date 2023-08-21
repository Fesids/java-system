import { useContext, useEffect, useState } from "react";
import { AppContext } from "../../Context/AppContext";
import { IChat } from "../../Interfaces/Chat";
import { IProfile } from "../../Interfaces/Profile";
import { Link } from "react-router-dom";

export interface ChatProps{
    data: IChat,
    currentProfileId: number
}

export const Chat = ({data, currentProfileId}:ChatProps) =>{

    const [profile, setProfile] = useState({} as IProfile);
    const {getProfileDetail} = useContext(AppContext);

    // PROFILE DETAIL
    useEffect(()=>{
        const profileId = data.members.find((profile)=> profile.id !==currentProfileId);
        const getProfileData = async ()=>{
            getProfileDetail(profileId.id)
            .then(resp => setProfile(resp));
        }

        getProfileData();
        console.log(profileId.id)
    })

    
    return(
        <div className="friends">
            <div className="pic">
                <img src="" alt=""></img>
            </div>
            <div className="name">
                <h5><Link to={`/chat/detail/${profile.id}`} className="link-chat">{profile.name}</Link></h5>
                <p>How are you doing today</p>
            </div>
            <div className="time_new_msg">
                <p>7:30am</p>
                <div className="msg">0</div>
            </div>
        </div>
    )

}