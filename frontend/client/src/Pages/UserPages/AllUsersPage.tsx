import { useContext, useEffect, useState } from "react"
import { AppContext } from "../../Context/AppContext"
import { IUser } from "../../Interfaces/User";
import { IProfile } from "../../Interfaces/Profile";
import { ProfileComp } from "../../Components/ProfileComp";

export const AllUsersPage = () =>{

    const {currentUserProfile, currentUserFriend} = useContext(AppContext);

    const {getAllUsers} = useContext(AppContext);
    const [users, setUsers] = useState([] as IUser[]);

    const [profiles, setProfile] = useState([] as IProfile[]);
    

    useEffect(()=>{

        getAllUsers().then(resp => setUsers(resp));
    }, []);

    //console.log(users);
    //console.log(currentUserProfile);
    console.log(currentUserFriend)
    return(
        <div>
            <div className="all-users-title">
                <h2>Search for a user</h2>
            </div>

            <div className="search-container">
                <form>
                    <input type="text"></input>
                </form>
            </div>

            <table className="table table-dark">
                <caption>List of users</caption>
                <thead className="bg-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                    </tr>
                    
                </thead>

                <tbody>
                    {users.map(u => <ProfileComp user_id={u?.user_id}/>)}
                    {/*<ProfileComp user_id={2}/>*/}
                </tbody>
            </table>
          
        </div>
    )
}