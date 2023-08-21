import { Link } from "react-router-dom"
import { IFriend } from "../Interfaces/Friend"
import { IProfile } from "../Interfaces/Profile"

export interface ConnectionProps{
    data: IFriend
}
export const Connection = ({data}: ConnectionProps) =>{

    return(
        <div className="connection-container">
             <div className="pic">
                <img src="" alt=""></img>
            </div>
            <div className="name">
                <Link to={`/chat/connections/detail/${data.profile.id}`}><h5 className="link-chat">{data.profile.name}</h5></Link>
                
            </div>
           
        </div>
    )
}