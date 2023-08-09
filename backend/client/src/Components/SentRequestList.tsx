import { IRequest } from "../Interfaces/Request"

interface ISentRequestListProps{
    list: IRequest[],
    dept_id: string

}

export const SentRequestList = ({list, dept_id}:ISentRequestListProps) =>{

    return(
        <div>
            {list.map(req =>
                <div>
                    <p>{req.subject}</p>
                </div>
                    )}
        </div>
    )
}