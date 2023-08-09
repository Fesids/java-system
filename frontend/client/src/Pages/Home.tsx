import { useContext, useState } from "react"
import { AppContext } from "../Context/AppContext"
import { IsEqualCustomizer } from "lodash";
import { ISiteType } from "../Interfaces/SiteType";
import { Link } from "react-router-dom";

export const HomePage = () =>{

    const {getAllSiteType} = useContext(AppContext);
    const [allSite, setAllSite] = useState([] as ISiteType[]);

    useState(()=>{
        getAllSiteType().then(resp => setAllSite(resp));
    })

    console.log(allSite);
    return(
        <div className="homepage">
            <h2>Home page</h2>

            <div className="border site-list">
                {allSite.map(ST => 
                    <div className="site-description">
                        <h3 className="st-description-title">{ST.description}</h3>
                        
                        <p className="btn-st-description link"><Link to={`/site/detail/${ST.std_id}`} className="btn-link">{ST.siteType}</Link></p>
                    </div>
                )}
            </div>
        </div>
    )
}