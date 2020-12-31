import React, { FunctionComponent } from 'react';
import "./NavBar.css";
import {Nav, Navbar} from "react-bootstrap";
import logo from "./../../common/logo.png";

interface OwnProps {}

type Props = OwnProps;

const NavBar: FunctionComponent<Props> = (props) => {
  return (
      <Navbar bg="white" expand="lg" className={"topbar navbar shadow"}>
          <Navbar.Brand><img alt={""} style={{
                width: "15%",
              marginLeft: "2vw",
              objectFit: 'cover'
          }} src={logo} /></Navbar.Brand>
      </Navbar>
  );
};

export default NavBar;