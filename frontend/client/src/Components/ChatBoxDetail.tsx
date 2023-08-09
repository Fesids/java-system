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

const sorter = (a:any, b:any) =>{
    return a.created_at - b.created_at;
}

const sortByCreated = ((arr:any) =>{
    arr.sort(sorter)
})

export const MessageList = ({receivedList, sentList, senderId}:MessageListComp) =>{

    let messageList = receivedList;
    let sortedList = null;
    if(sentList){
        messageList = receivedList.concat(sentList);
        messageList = messageList.sort((a, b)=> a.id - b.id);
        sortedList = messageList.sort();
        
    }

    console.log(sortedList)
    

    return(
        <div className="chat-box">
            {messageList.length?messageList.map((msg)=>(
                <MessageContainer data={msg} senderId={senderId?senderId:0}/>
            )): <p>No messages avaible</p>}
        </div>
    )
}