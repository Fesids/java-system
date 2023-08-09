import { IFriend } from "./Friend";

export interface IProfile{
    id: number,
    user_id: number,
    name: string,
    pic: string,
    friends: Array<IFriend>
}