import { useContext } from "react";
import { IRequestClient } from "../Interfaces/RequestClient";
import { AppContext } from "../Context/AppContext";
import { Link } from "react-router-dom";

export interface CLRProps {
    list: IRequestClient[],
    dept_name: string
}
export const ClientRequestList = ({list, dept_name}: CLRProps) =>{
    
    
    return(
        <div className="req-list-container">
            {list.length?list.map(rc=>
                <div className="req-container">
                    <Link to={`/departments/${dept_name}/client_requests/${rc.request_id}`} className="link">{rc.subject}</Link>
                </div>):<p>No requests found</p>}
        </div>
    )
}