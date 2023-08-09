import { IMessage } from "../Interfaces/Message"

interface MessageComp{
    data: IMessage,
    senderId: number
}

interface MessageListComp{
    receivedList: IMessage[],
    sentList?: IMessage[],
    senderId?: number
}


export const MessageContainer = ({data, senderId}: MessageComp)=>{
    return(
       <>
        {data.msg_sender.id == senderId?
        <div className="chat-box-sent">
            <p>{data.body}</p>
        </div>:
        <div className="chat-box-received">
            <p>{data.body}</p>
        </div>
        
        }
       </>
    )
}

export const MessageList = ({receivedList, sentList, senderId}:MessageListComp) =>{

    let messageList = receivedList;
    if(sentList){
        messageList = receivedList.concat(sentList);
    }
    

    return(
        <div className="chat-box">
            {messageList.map((msg)=>(
                <MessageContainer data={msg} senderId={senderId?senderId:0}/>
            ))}
        </div>
    )
}