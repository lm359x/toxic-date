import classes from './Message.module.css'
import {Col, Row} from "reactstrap";

function isMine(usr,id){
    return(usr.messages.filter(m=>m.id===id)==0)
}

export default function Message(props){
    console.log(props.message)
    console.log(isMine(props.usr, props.message.id))
    if(isMine(props.usr,props.message.id)){
        return (
            <Row className={`${classes.chatMessage} ${classes.mine} justify-content-between`}>
                <Col>{props.message.text}</Col>
                <Col>{props.message.creationDate}</Col>
            </Row>
        );
    }else {
        return (
            <Row className={`${classes.chatMessage} ${classes.others} justify-content-between`}>
                <Col>{props.message.text}</Col>
                <Col>{props.message.creationDate}</Col>
            </Row>
        );
    }
}