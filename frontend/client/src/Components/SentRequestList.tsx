import { Link } from "react-router-dom"
import { IRequest } from "../Interfaces/Request"

interface ISentRequestListProps{
    list: IRequest[],
    dept_name: string

}

export const SentRequestList = ({list, dept_name}:ISentRequestListProps) =>{

    return(
        <div className="req-list-container">
            {list.length?list.map(req =>
                <div className="req-container">
                    <Link to={`/departments/${dept_name}/requests/${req.request_id}`}>{req.subject}</Link>
                </div>
                    ): <p>No requests found</p>}
        </div>
    )
}