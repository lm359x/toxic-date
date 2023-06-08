import {Nav, NavItem} from "reactstrap";
import {Link} from "react-router-dom";


export default function Navbar(){
    return(
        <div className="my-font">
            <Nav vertical={true}>
                    <Link to={"search"}><NavItem className="no-decoration">SEARCH</NavItem></Link>
                    <Link to={"settings"}><NavItem className="no-decoration">SETTINGS</NavItem></Link>
                    <Link to={"chats"}><NavItem className="no-decoration">CHATS</NavItem></Link>
                
            </Nav>
        </div>
    )
}