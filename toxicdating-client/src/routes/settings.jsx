import React from 'react'
import {Navbar} from "../components/navbar/Navbar";
import {Card, CardBody, CardImg, Col, Container, Form as FormStrap, FormGroup, Input, Label, Row} from "reactstrap";
import UsrService from "../services/UsrService";
import {redirect, useLoaderData} from "react-router";
import {getAge} from "../utils";
import {Form} from "react-router-dom";
import axios from "axios";
import {API_URL, USER_ID} from "../http";


export async function loader() {
    const usr = await new UsrService().fetchUser();
    console.log(usr);
    return {usr};
}

export async function action({request, params}) {
    const formData = await request.formData();
    let updates = Object.fromEntries(formData);
    let bio = updates.bio;
    let active = updates.active;
    await axios.put(`${API_URL}users/${USER_ID}`, {bio,active});
    return redirect(`/`);
}


function Settings() {
    const {usr} = useLoaderData();

    return (
        <Container>
            <Row className="justify-content-center">
                <Col className="col-10 w-50 pl-5">
                    <Row className="text-center">
                        <h2 className="text-center my-font font-weight-bold"> TOXIC SETTINGS</h2>
                    </Row>
                    <Row className="my-font">
                        {usr ?
                            <Card body>
                                <CardBody>
                                    <Row>
                                        <Col className="col-3 text-center font-weight-bold">Имя</Col>
                                        <Col className="col-3 text-center font-weight-bold">Возраст</Col>
                                        <Col className="col-3 text-center font-weight-bold">Пол</Col>
                                        <Col className="col-3 text-center font-weight-bold">Адрес</Col>
                                    </Row>
                                    <Row>
                                        <Col className="col-3 text-center">{usr.name}</Col>
                                        <Col className="col-3 text-center">{getAge(usr.dateOfBirth)}</Col>
                                        <Col className="col-3 text-center">{usr.gender}</Col>
                                        <Col className="col-3 text-center">{usr.email}</Col>
                                    </Row>
                                </CardBody>
                                <CardImg
                                    alt="Card image cap"
                                    src={usr.pictures[0]}
                                    style={{
                                        height: 180
                                    }}
                                    top
                                    width="100%">

                                </CardImg>
                            </Card>

                            :
                            <p>no usr</p>}
                    </Row>
                    <Row>
                        <Form method="post" id="contact-form" className="border">
                            <FormStrap tag={"div"}>
                                <FormGroup row>
                                    <Label for="active" sm={2}>Active</Label>
                                    <Col sm={10}>
                                        <Input
                                            id="active"
                                            placeholder="Active"
                                            aria-label="Active"
                                            type="checkbox"
                                            name="active"
                                            defaultValue={usr.active}
                                        />
                                    </Col>

                                </FormGroup>
                                <FormGroup row>
                                    <Label for="bio" sm={2}>BIO</Label>
                                    <Col sm={10}>
                                        <Input
                                            id="bio"
                                            type="text"
                                            name="bio"
                                            defaultValue={usr.bio}
                                        />
                                    </Col>

                                </FormGroup>

                            </FormStrap>
                            <p>
                                <button type="submit">Save</button>
                            </p>
                        </Form>
                    </Row>
                </Col>
            </Row>
        </Container>
    )
}

export default Settings;