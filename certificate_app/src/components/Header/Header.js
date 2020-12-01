import React from "react";
import Navbar from "react-bootstrap/Navbar";
import NavbarLogo from "../../assets/img/nav-logo.png";
import "./Header.css";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";

function Header() {
    return(
        <Navbar fixed="top" bg="white">
            <Navbar.Brand>
                <img
                    src={NavbarLogo}
                    width="200"
                    className="d-inline-block align-top"
                    alt="React Bootstrap logo"
                />
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                <Nav className="">
                    <Nav.Link href="#home">MAP</Nav.Link>
                    <Nav.Link href="/">HOME</Nav.Link>
                    <NavDropdown title="ENG" id="basic-nav-dropdown">
                        <NavDropdown.Item href="#action/3.1">ENG</NavDropdown.Item>
                    </NavDropdown>
                    <Nav.Link href="#link">HELP</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default Header;