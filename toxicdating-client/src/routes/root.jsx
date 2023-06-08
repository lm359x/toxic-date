import React from 'react'
import {Col, Container, Row} from "reactstrap";
import Header from "../components/header/Header";
import Navbar from "../components/navbar/Navbar";
import {Outlet} from "react-router";
import classes from './root.module.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import WebFont from "webfontloader";




class Root extends React.Component {
    componentDidMount() {
        WebFont.load({
            google: {
                families: ['Nunito']
            }
        })
    }

    render() {
        return (
            <Container className="bg-light border">
                <Row>
                    <Col className={classes.bg1}>
                        <Header/>
                    </Col>
                </Row>
                <Row>
                    <Col className={`${classes.bg2}`}>
                        <Navbar/>
                    </Col>
                    <Col className="bg-light col-9">
                        <Outlet/>
                    </Col>
                </Row>
            </Container>

        )
    }
}

export default Root