import { IProfile } from "./Profile";

export interface IMessage{
    id: number,
    msg_sender: IProfile,
    msg_receiver: IProfile,
    body: string,
    seen: boolean
}