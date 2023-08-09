export interface IRequestClient{
    request_id: number,
    destination_dept_id: string,
    subject: string,
    body: string,
    created_at: string,
    client_email: string
}

export interface IRequestClientCreateBody extends Omit<IRequestClient, "request_id"|"destination_dept_id"|"created_at">{

}