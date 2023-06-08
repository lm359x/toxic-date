import React from "react";
import {Col, Container, Row} from "reactstrap";
import {Link} from "react-router-dom";
import classes from './ChatPreview.module.css'

export default function ChatPreview(props) {
    return (
        <Container >
            <Row className={`align-items-center border rounded bg1 ${classes.bg3} p-sm-1 ${classes.chatRow}`}>
                <Col>
                    <img className="rounded-circle img-fluid w-25"
                         src={props.img}
                         alt="CHAT IMAGE"
                    />
                </Col>
                <Col className="my-font"><h5>{props.name}</h5></Col>
            </Row>

        </Container>
    )
}