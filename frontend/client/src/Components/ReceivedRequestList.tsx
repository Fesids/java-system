
import { IRequest } from "../Interfaces/Request"
import { AppContext } from "../Context/AppContext"
import { Link, useParams } from "react-router-dom"

interface ReceivedRequestListProps{
    list: IRequest[],
    dept_name: string
}

export const ReceivedRequestList = ({list, dept_name}:ReceivedRequestListProps) =>{
        
        return(
            <div className="req-list-container">
                {list.length?list.map(req => 
                    <div className="req-container"><Link to={`/departments/${dept_name}/requests/${req.request_id}`}>{req.subject}</Link></div>
                    )
                    
                    :
                    
                    <p>No requests</p>
                    
                }
            </div>
        )

}