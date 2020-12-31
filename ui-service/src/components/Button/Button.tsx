import React, { FunctionComponent } from 'react';
import "./Button.css";

interface OwnProps {
  label: string;
}

type Props = OwnProps;

const Button: FunctionComponent<Props> = (props) => {
  return (
      <button className="btn btn-primary btn-block text-white btn-user" type="submit">{props.label}</button>
  );
};

export default Button;
