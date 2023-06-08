import {Button, Card, CardBody, CardText, CardTitle} from "reactstrap";
import {getAge} from "../../utils";




export default function UserInfo(props) {
    return (


        <Card body
        className="my-font">
            <CardTitle tag="h5" className="text-center">
                {props.usrInfo.name}
            </CardTitle>
            <CardBody>
                {props.usrInfo.gender}
                <t></t>
                {getAge(props.usrInfo.dateOfBirth)}
            </CardBody>
            <img
                alt="Card"
                src={props.usrInfo.pictures[0]}

            />
            <CardText tag="h6">
                {props.usrInfo.bio}
            </CardText>
            <Button>
                Like!
            </Button>
        </Card>
    )
}