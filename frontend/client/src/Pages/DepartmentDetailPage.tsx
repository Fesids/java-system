import { useContext, useEffect, useState } from "react"
import { AppContext } from "../Context/AppContext"
import { IDepartment } from "../Interfaces/Department";
import { useParams } from "react-router";
import { DepartmentComp } from "../Components/DepartmentComp";
import { IUser } from "../Interfaces/User";
import { IRequestClient } from "../Interfaces/RequestClient";
import { ClientRequestList } from "../Components/ClientRequestList";
import { IRequest } from "../Interfaces/Request";
import { ReceivedRequestList } from "../Components/ReceivedRequestList";
import { Link } from "react-router-dom";
import { SentRequestList } from "../Components/SentRequestList";


export const DepartmentDetail = () =>{
    const [department, setDepartment] = useState({} as IDepartment);
    const [users, setUsers] = useState([] as IUser[]);
    const {id} = useParams();
    const {currentUser,getDepartment, getUsersByDeptId, getAllClientRequest, getReceivedRequests, getSentRequests} = useContext(AppContext);

    const [clientRequestList, setClientRequestList] = useState([] as IRequestClient[]);
    const [deptRequestReceived, setDeptRequestReceived] = useState([] as IRequest[]);
    const [deptRequestSent, setDeptRequestSent] = useState([] as IRequest[]);
    const [showCRList, setShowCRList] = useState(false);
   // const [reqReceivedList, setReqReceivedList] = useState([] as IRequest[]);
    const [showReceveivedReqList, setShowReceivedReqList] = useState(false);
    const [showSentReqList, setShowSentList] = useState(false);


    // ##### SET DEPARTENT
    useEffect(() =>{
        let dep_id = '';
        if(id){
            dep_id = id;
        }
        getDepartment(dep_id).then(resp => setDepartment(resp));
    }, []);

    // get user by user by dept
    useEffect(()=>{
        let dep_id = '';
        if(id){
            dep_id = id;
        }
        getUsersByDeptId(dep_id).then(resp => setUsers(resp));
    }, []);

    // SET CLIENT REQUEST
    useEffect(()=>{
        let dep_id = '';
        if(id){
            dep_id = id;
        }
        getAllClientRequest(dep_id).then(resp => setClientRequestList(resp));
    }, []);

    //* SET RECEIVED REQUEST
    useState(()=>{
        let dep_id = '';
        if(id){
            dep_id = id;
        }

        getReceivedRequests(dep_id).then(res => setDeptRequestReceived(res));
        
    })

    const handleClientRequestClick = () =>{
        setShowCRList(!showCRList);
    }

    const handleReceivedRequestClick = () =>{
        setShowReceivedReqList(!showReceveivedReqList);
    }

    const handleSentRequestList = () =>{
        setShowSentList(!showSentReqList);
    }


    useState(()=>{
        let dep_id = '';
        if(id){
            dep_id = id;
        }

        getSentRequests(dep_id).then(resp => setDeptRequestSent(resp));
    })
    console.log(deptRequestSent);

    return(
        <div>
            <div className="department-header">
                <h2>{department?.department_name} department</h2>
                <h6>Welcome {currentUser?.username} !!!</h6>
            </div>

            <h3 className="request-header-text">Check department requests</h3>


            

            <div className="requests-content-container">
                <div className="request-container border">
                    <div className="client-request-header">CLIENT REQUEST</div>
                    <div className="request-button-container bg-dark">
                        <button value={"sales"} onClick={handleClientRequestClick} className="btn btn-dark">show</button>
                    </div>
                    {showCRList?<ClientRequestList list={clientRequestList} dept_name={department.department_name}/>:""}
                </div>

                <div className="request-container border">
                    <div className="client-request-header">RECEIVED REQUESTS</div>
                    <div className="request-button-container bg-dark">
                        <button value={"sales"} onClick={handleReceivedRequestClick} className="btn btn-dark">show</button>
                        <button className="btn btn-dark btn-request"><Link to={`/departments/${department.id}/request/new`} >+ add request</Link></button>
                    </div>
                    
                   
                    {showReceveivedReqList?<ReceivedRequestList list={deptRequestReceived} dept_name={department.department_name}/> :""}
                </div>

                <div className="request-container">
                    <div className="client-request-header">Sent Requests</div>
                    <div className="request-button-container bg-dark">
                        <button value={"sales"} onClick={handleSentRequestList} className="btn btn-dark">show</button>
                        <button className="btn btn-daek btn-request btn-link"><Link to={`/departments/${department.id}/request/new`} className="link">+ add request</Link></button>
                    </div>
                    <SentRequestList list={deptRequestSent} dept_name={department.department_name}/>
                </div>
            </div>
        </div>
    )
}