import { useContext, useState } from "react"
import { useParams } from "react-router"
import { AppContext } from "../Context/AppContext";
import { IRequestClient } from "../Interfaces/RequestClient";

export const ClientRequesDetailPage = () =>{

    const {id, dept_name} = useParams();
    const {getClientRequestDetail, createRequest, deleteClientRequest} = useContext(AppContext);

    const [clientRequest, setClientRequest] = useState({} as IRequestClient);
    const [showMore, setShowMore] = useState(false);


    useState(()=>{
        let cr_id = 0;
        if(id){
            cr_id = parseInt(id);
        };

        getClientRequestDetail(cr_id).then(resp => setClientRequest(resp));
    });

    console.log(clientRequest);

    const sendToAnalise = (e:any) =>{
        e.preventDefault();
        const b = {
            sender_dept_id: clientRequest.destination_dept_id,
            destination_dept_id: "64772d18f15da97c1d03ee80",
            subject: clientRequest.subject,
            body: clientRequest.body,
           
        }

        createRequest(1, b);
        deleteClientRequest(clientRequest.request_id);

    }

    let body = ""
    if(clientRequest.body){
        body = clientRequest.body
    }

    return(
        <div className="request-detail-container">
            <div className="request-detail">
                
               
                <p className="subject">subject {clientRequest.subject}</p>
                <div className="request-body">
                    {showMore?body: body.substring(0, 100)} <p onClick={()=> setShowMore(!showMore)}>{!showMore?"show more":"show less"}</p>

                </div>
                <p>client email : {clientRequest.client_email}</p>
                <p>created at {clientRequest.created_at}</p>

                <div className="buttons-container">
                    <button className="btn btn-outline-danger">reject</button>
                    <button className="btn btn-outline-success btn-analysis" onClick={(e)=>sendToAnalise(e)}>send to analisys</button>
                </div>
            </div>

        </div>
        
    )
}