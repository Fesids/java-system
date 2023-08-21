import { useContext, useEffect, useState } from "react"
import { IProfile } from "../Interfaces/Profile"
import { IUser } from "../Interfaces/User"
import { AppContext } from "../Context/AppContext"
import { useSearchParams } from "react-router-dom"
import { IFriend } from "../Interfaces/Friend"
import { assert } from "console"

interface ProfileCompProps{
   // user_id:number
   data?:IProfile,
   user_id:number

}


export const ProfileComp =({user_id}:ProfileCompProps)=>{

    const {getProfileDetail, getUserProfileByUserId, getFriendByProfileId, currentUser, createConversation, currentUserProfile, currentUserFriend} = useContext(AppContext);

    const [profile, setProfile] = useState({} as IProfile);
    //const [currentUserProfile, setCurrentUserProfile] = useState({} as IProfile);

    const [friend, setFriend] = useState({} as IFriend);

    const handleAddFriend = (e:any) =>{

        e.preventDefault();
        let currentUserProfileId = 0;
        if(currentUserProfile){
            currentUserProfileId = currentUserProfile.id;
        }
        createConversation(currentUserProfileId, friend.id);
        /*if(currentUserFriend)
        createConversation(profile.id, currentUserFriend?.id);
        /*let profileId = 0;
        let friendID = 0;
        if(currentUserProfile?.id && friend.id){
            profileId = currentUserProfile.id;
            friendID = friend.id
            createConversation(profileId, friendID);
        }*/
       
        
    }

    useEffect(()=>{
        let prof_id = 0;
        if(profile.id){
            prof_id = profile.id;
            getFriendByProfileId(prof_id).then(resp => setFriend(resp))
        }
       
    })



    useEffect(()=>{
        getUserProfileByUserId(user_id).then(resp => setProfile(resp));
    }, []);

    /*console.log(friend);
    console.log(currentUserProfile);*/
    

    return(
        <>
            {profile?
            <tr >
                <td>{profile.id}</td>
                <td>{profile.name} - <p className="btn btn-dark" onClick={(e)=> handleAddFriend(e)}>+ add</p></td>
            </tr>
            
    
            :
            
            <tr>
                <td>not found</td>
                <td>not found</td>
            </tr>
            }
        </>
       
        
    )
}