import React from 'react'
import {Col, Container, FormGroup, Input, Label, Row, Form as FormStrap} from "reactstrap";
import ChatPreview from "../components/chatPreview/ChatPreview";
import UsrService from "../services/UsrService";
import {redirect, useLoaderData} from "react-router";
import MessageContainer from "../components/messageContainer/MessageContainer";
import {Form} from "react-router-dom";
import axios from "axios";
import {API_URL, USER_ID} from "../http";

export async function loader({params}) {
    const {usr, chat} = await new UsrService().fetchChat(params.id);
    return {usr, chat};
}

export async function action({request,params}){
    const formData = await request.formData();
    let updates = Object.fromEntries(formData);
    let text = updates.text;
    let senderId=USER_ID;
    let chatId = params.id;
    await axios.post(`${API_URL}messages/`,{text,senderId,chatId});
    return redirect(`/chat/{params.id}`);

}

export default function Chat() {
    const {usr, chat} = useLoaderData();
    return (
        <Container>
            <Row className="justify-content-center">
                <Col className="col-10 w-50 pl-5">
                    <Row className="text-center">
                        <h2 className="text-center my-font font-weight-bold"> TOXIC CHAT</h2>
                    </Row>

                </Col>
            </Row>
            <Row className="align-items-center justify-content-md-start">
                <Col className="col-9">
                    <h3>{usr.name}</h3>
                </Col>
                <Col className="col-3">
                    <img className="rounded-circle img-fluid w-25"
                         src={usr.pictures[0]}
                         alt="CHAT IMAGE"
                    />
                </Col>
            </Row>
            <Row>
                <MessageContainer messages={chat.messages} usr={usr}/>
            </Row>
            <Row className="justify-content-between align-items-center">
                <Form method="post" id="contact-form" className="border pt-2">
                    <FormStrap tag={"div"}>
                        <FormGroup row>
                            <Col sm={10}>
                                <Input
                                    id="text"
                                    type="text"
                                    name="text"
                                    placeholder="Type your message!"
                                    defaultValue=""
                                />
                            </Col>
                            <Col sm={2}>
                                <p>
                                    <button type="submit" className="rounded">Send</button>
                                </p>
                            </Col>
                        </FormGroup>
                    </FormStrap>
                </Form>
            </Row>
        </Container>
    );
}