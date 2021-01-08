import React, { FunctionComponent } from 'react';
import styled from "styled-components";

const ButtonStyled = styled.button`
  font-size: .8rem;
  border-radius: 10px;
  outline: none;
  border: none;
  padding: .75rem 1rem;
  margin-bottom: 2vh;
  background: rgb(0,155,255);
  background: linear-gradient(90deg, rgba(0,155,255,1) 0%, rgba(0,155,255,0.8211659663865546) 100%);
  color: #fff!important;
  box-shadow: 0 0 0 .2rem rgba(105,136,228,.5);
`

interface OwnProps {
  label: string;
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
  return (
      <ButtonStyled>{props.label}</ButtonStyled>
  );
};

export default Button;
