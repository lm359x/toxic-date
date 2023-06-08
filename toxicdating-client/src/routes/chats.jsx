import React from 'react'
import {Navbar} from "../components/navbar/Navbar";
import {Col, Container, Row} from "reactstrap";
import UsrService from "../services/UsrService";
import {useLoaderData} from "react-router";
import ChatPreview from "../components/chatPreview/ChatPreview";
import {Link} from "react-router-dom";
import classes from './chats.module.css'

export async function loader() {
    const chats = await new UsrService().fetchChats();
    console.log(chats);
    return {chats};
}

function Chats(){
        const {chats}=useLoaderData();
        return(
            <Container>
                <Row className="justify-content-center">
                    <Col className="col-10 w-50 pl-5">
                        <Row className="text-center">
                            <h2 className="text-center my-font font-weight-bold"> TOXIC CHATS</h2>
                        </Row>

                    </Col>
                </Row>
                <Row className={classes.noDecorationRow}>
                    {chats.length !== 0 ?
                        chats.map(c => (
                            <Link key={c.chat.id} to={`/chats/${c.chat.id}`}>
                                <ChatPreview name={c.usr.name}
                                             img={c.usr.pictures[0]}
                                             chatId={c.chat.id}
                                />
                            </Link>

                            )) : <p>No users</p>
                    }
                </Row>
            </Container>
        )

}

export default Chats;