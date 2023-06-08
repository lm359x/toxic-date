import React from 'react'
import {Navbar} from "../components/navbar/Navbar";
import UsrService from "../services/UsrService";
import {useLoaderData} from "react-router";
import UserInfo from "../components/userInfo/UserInfo";
import {Col, Container, Row} from "reactstrap";

export async function loader() {
    const users = await new UsrService().fetchUsers();
    console.log(users);
    return {users};
}


function Search() {
    const {users} = useLoaderData();

    return (
        <Container>
            <Row className="justify-content-center">
                <Col className="col-10 w-50 pl-5">
                    <Row className="text-center">
                        <h2 className="text-center my-font font-weight-bold"> TOXIC SEARCH</h2>
                    </Row>
                    {users.length !== 0 ?
                        users.map(u => (
                            <Row key={u.id} className="mt-sm-1">
                                <UserInfo usrInfo={u}/>
                            </Row>)) : <p>No users</p>
                    }
                </Col>
            </Row>

        </Container>

    )

}

export default Search;