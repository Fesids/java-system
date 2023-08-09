import axios from "axios";
import React, { createContext, useEffect, useState } from "react";
import { IUser, LoginResponseData } from "../Interfaces/User";
import { IDepartment } from "../Interfaces/Department";
import { SearchQuery } from "../Interfaces/Search";
import { ISiteType } from "../Interfaces/SiteType";
import { IRequestClient, IRequestClientCreateBody } from "../Interfaces/RequestClient";
import { ICreateRequest, IRequest, ISendRequest } from "../Interfaces/Request";
import { IProfile } from "../Interfaces/Profile";
import { IncomingMessage } from "http";
import { IMessage } from "../Interfaces/Message";
import { IFriend } from "../Interfaces/Friend";

interface AppContextProps{

    currentUser: LoginResponseData | null,
    currentUserProfile: null | IProfile,
    currentUserFriend: null | IFriend,
    setCurrentUserProfile:(currentUserProfile:IProfile) => void
    register(e:any, user:any): void,
    login(e:any, user:any): Promise<LoginResponseData>,
    getAllUsers(): Promise<IUser[]>,


    getDepartmentList(): Promise<IDepartment[]>,
    getDepartment(dept_id:string): Promise<IDepartment>,
    getUsersByDeptId(dept_id:string): Promise<IUser[]>,
    searchUser(char:SearchQuery): Promise<IUser[]>,
    getAllSiteType(): Promise<ISiteType[]>,
    getSiteDetail(id:string): Promise<ISiteType>,
    createRequestClient(body: any): Promise<IRequestClientCreateBody>,
    getAllClientRequest(id:string): Promise<IRequestClient[]>,

    getReceivedRequests(dept_id:string): Promise<IRequest[]>,
   
    getClientRequestDetail(req_id:number): Promise<IRequestClient>,
    getRequestDetail(req_id:number): Promise<IRequest>,
    createRequest(user_id:number, body:any): Promise<any>;

    deleteClientRequest(req_id:number): Promise<any>;
    deleteRequest(req_id:number): Promise<any>;
    updateDestIdAndSenderId(/*destination_dept_id:string, sender_dept_id:string,*/up_body:ISendRequest, request_id:number): Promise<IRequest>;
    getSentRequests(dept_id:string): Promise<IRequest[]>,
    getUserProfileByUserId(user_id: number): Promise<IProfile>,
    getProfileDetail(profile_id: number): Promise<IProfile>,

    getSentMessages(sent:number, receiver:number): Promise<Array<IMessage>>,
    getReceivedMessages(receiver:number, sent:number): Promise<Array<IMessage>>,
    sentMessage(body:any): Promise<IMessage>;
    createConversation(profile_id:number, friend_id:number): Promise<IProfile>,

    getFriendByProfileId(profile_id:number): Promise<IFriend>;

}

export const AppContext = createContext({} as AppContextProps);


export const AppContextProvider = ({children}:React.PropsWithChildren)=>{
    const [currentUser, setCurrentUser] = useState<null | LoginResponseData>(JSON.parse(localStorage.getItem("currentuser") || "{}"));
    const [currentUserProfile, setCurrentUserProfile] = useState<null | IProfile>({} as IProfile || "{}");
    const [currentUserFriend, setCurrentUserFriend] = useState<null | IFriend>({} as IFriend || "{}");
    const [user, setUser] = useState({} as IUser);

    //SET CURRENT USER FRIEND
    useEffect(()=>{
        axios.get("http://localhost:8088/api/friend/profile/"+currentUserProfile?.id)
        .then(resp => setCurrentUserFriend(resp.data));

    })

    //SET CURRENT USER PROFILE
    useEffect(()=>{
        
        axios.get("http://localhost:8088/api/profile/user_id/"+currentUser?.user_id)
        .then(resp => setCurrentUserProfile(resp.data));

            
    }, [])



    const getAllUsers = async  () =>{

        try{
            const resp = await axios.get("http://localhost:8083/api/auth/all");
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve users list");
        }

    }


    const register = async (e:any, user:any)=>{
        e.preventDefault();
        try{
            const resp = await axios.post("http://localhost:8083/api/auth/new/external", user);
        } catch(err){
            console.log(err);
        }
    }

    const login = async (e:any, user:any) =>{
        e.preventDefault()
        try{
            const resp = await axios.post("http://localhost:8083/api/auth/login", user);
            //setUser(resp.data.body);
            localStorage.setItem("currentuser", JSON.stringify(resp.data));
            setCurrentUser(resp.data.body);
            return resp.data
            
        } catch(err){
            console.log(err)
        }
    }

    const getDepartmentList = async () =>{
        const resp = await axios.get("http://localhost:8081/api/department/list");
        return resp.data;
    }

    const getDepartment = async (dept_id:string) =>{
        try{
            const department = await axios.get("http://localhost:8081/api/department/detail/"+dept_id);
            return department.data;
        }catch(err){
            throw new Error(`department with id ${dept_id} not found`);
        }
    }

    const getUsersByDeptId = async (dept_id:string) =>{
        try{
            const usersByDept = await axios.get("api/auth/users/department/"+dept_id);
            return usersByDept.data;
        }catch(err){
            throw new Error("")
        }

    }

    const getAllSiteType = async ()=>{
        try{
            const allSiteType = await axios.get("http://localhost:8084/api/ST/list");
            return allSiteType.data;
        }catch(err){
            throw new Error("Something went  wrong trying to retrieve siteTypeList");
        }
    }

    const getSiteDetail = async (id:string) =>{
        try{
            const getSiteDetail = await axios.get(`http://localhost:8084/api/ST/detail/${id}`);
            return getSiteDetail.data;
        }catch(err){
            throw new Error("site with id "+ id + " couldn't be retrieved");
        }
    }
    

    const searchUser = async (char:SearchQuery) =>{
        const resp = await axios.post("api/auth/search/user", char);

        return resp.data;

    }

    const createRequestClient = async (body:any) =>{
        try{
            const res = await axios.post("http://localhost:8087/api/request/client/send", body);
            return res.data; 
        } catch(err){
            throw new Error("failed to create request client");
        }
    }

    const getAllClientRequest = async (id:string) =>{
        try{
            const resp = await axios.get("http://localhost:8087/api/request/list/department/"+id);
            return resp.data;
        } catch(err){
            throw new Error("axios failed to retrieve client");
        }
    }

    const getReceivedRequests = async (dept_id:string) =>{
        try{
         const res = await axios.get("http://localhost:8087/api/request/list/received/department/"+dept_id);
         return res.data;
        }catch(err){
            throw new Error("failed to retrieve received requests")
        }
    }

    const getClientRequestDetail = async (req_id:number): Promise<IRequestClient> =>{
        try{
            const resp = await axios.get("http://localhost:8087/api/request/clientRequest/detail/"+req_id);
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve client request detail");
        }
    }

    const getRequestDetail = async (dept_id:number): Promise<IRequest> =>{
        try{
            const resp = axios.get("http://localhost:8087/api/request/all/detail/"+dept_id);
            return (await resp).data;
        }catch(err){
            throw new Error("Failed to retrieve request")
        }
    }

    const createRequest = async (user_id:number, body:any) =>{
        try{
            const resp = await axios.post("http://localhost:8087/api/request/new/"+user_id, body);
            return resp.data;
        }catch(err){
            throw new Error("failed to create request")
        }
    }

    const deleteClientRequest = async (req_id:number) =>{
        await axios.delete(`http://localhost:8087/api/request/clientRequest/delete/${req_id}`)
            .catch(resp => resp);
    }

    const deleteRequest = async (req_id:number) =>{
        await axios.delete(`http://localhost:8087/api/request/requests/delete/${req_id}`)
        .catch(resp => resp);
    }

    const  updateDestIdAndSenderId = async (up_body:ISendRequest, request_id:number): Promise<IRequest>=>{
        try{
            const res = axios.put(`http://localhost:8087/api/request/update/request/${request_id}`);
            return (await res).data;
        }catch(err){
            throw new Error("")
        }
    }
    
   
    const getSentRequests = async (dept_id:string)=>{
        try{
            const resp = await axios.get("http://localhost:8087/api/request/list/sent/department/"+dept_id);
            return resp.data
        }catch(err){
            throw new Error("Deu erro, faz parte");
        }
    }

    const getUserProfileByUserId = async (user_id: number): Promise<IProfile> =>{
        try{
            const resp = await axios.get("http://localhost:8088/api/profile/user_id/"+user_id);
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve profile");
        }
    }

    const getProfileDetail = async (profile_id: number) =>{
        try{
            const resp = await axios.get("http://localhost:8088/api/profile/detail/"+profile_id);
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve profile")
        }
    }

    const getSentMessages = async (sent:number, receiver:number): Promise<Array<IMessage>> =>{
        try{
            const resp = await axios.get(`http://localhost:8088/api/message/list/sent/${sent}/receiver/${receiver}`);
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve messages")
        }
    }

    const getReceivedMessages = async (receiver:number, sent:number): Promise<Array<IMessage>> =>{
        try{
            const resp = await axios.get(`http://localhost:8088/api/message/list/receiver/${receiver}/sent/${sent}`)
            return resp.data;
        }catch(err){
            throw new Error("Failed to retrieve messages")
        }
    }

    const sentMessage = async (body:any): Promise<IMessage> =>{
        try{
            const resp = await axios.post("http://localhost:8088/api/message/new", body);
            return resp.data;
        }catch(err){
            throw new Error("Failed to create messsage");
        }
    }

    const createConversation = async (profile_id:number, friend_id:number) =>{
        try{
            const resp = await axios.patch(`http://localhost:8088/api/profile/${profile_id}/friend/${friend_id}`);
            return resp.data;
        }catch(err){
            throw new Error("Failed to create conversation");
        }
    }

    const getFriendByProfileId = async (profile_id:number) =>{
        try{
            const resp = await axios.get("http://localhost:8088/api/friend/profile/"+profile_id);

            return resp.data;

        }catch(err){
           throw new Error("Failed to retrieve friend"); 
        }
    }


    return(

        <AppContext.Provider value={{register, login, getDepartmentList, getDepartment, 
            getUsersByDeptId, searchUser, getAllSiteType, getSiteDetail, 
            createRequestClient, getAllClientRequest, getReceivedRequests, getClientRequestDetail,getRequestDetail,
            createRequest, deleteClientRequest, deleteRequest, updateDestIdAndSenderId, getSentRequests, currentUser, getUserProfileByUserId, getProfileDetail,
            getSentMessages, getReceivedMessages, sentMessage, createConversation, getFriendByProfileId,getAllUsers, currentUserProfile, setCurrentUserProfile, currentUserFriend}}>
            {children}
        </AppContext.Provider>
    )

}
