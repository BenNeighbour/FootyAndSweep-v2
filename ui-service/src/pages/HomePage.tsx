import React, { FunctionComponent } from 'react';

interface OwnProps {}

type Props = OwnProps;

const HomePage: FunctionComponent<Props> = (props) => {
  return (
      <h1>Home page!</h1>
  );
};

export default HomePage;
