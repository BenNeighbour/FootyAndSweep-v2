import React, { FunctionComponent } from 'react';
import {Navbar} from "react-bootstrap";
import logo from "./../../common/logo.png";
import styled from "styled-components";

const NavbarStyled = styled.div`
  height: 4.375rem;
  background: linear-gradient(90deg, rgba(0, 155, 255, 1) 0%, rgba(0, 155, 255, 0.8211659663865546) 100%) left top #ffffff no-repeat;
  background-size: 100% 5px;
  background-color: #fff !important;
  box-shadow: 0 .15rem 1.75rem 0 rgba(58, 59, 69, .15) !important;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
`;

interface OwnProps {}

type Props = OwnProps;

const NavBar: FunctionComponent<Props> = (props) => {
  return (
      <NavbarStyled>
          <Navbar.Brand><img alt={""} style={{
                width: "15%",
              marginLeft: "2vw",
              objectFit: 'cover'
          }} src={logo} /></Navbar.Brand>
      </NavbarStyled>
  );
};

export default NavBar;