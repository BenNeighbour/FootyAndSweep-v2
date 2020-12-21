import React, { FunctionComponent } from 'react';
const { GreetingServiceClient } = require('../proto/greeting_service_grpc_web_pb');
const { HelloRequest, HelloResponse } = require('../proto/greeting_service_pb');

interface OwnProps {}

type Props = OwnProps;

const client = new GreetingServiceClient('http://localhost:9090', null, null);

const callService = () => {
  const request = new HelloRequest();
  request.setName("Ben The Neighbour")

  client.greeting(request, {}, (err: any, response: any) => {
    if (response == null) {
      console.log(err);
    } else {
      console.log(response);
    }
  });
}

const HomePage: FunctionComponent<Props> = (props) => {
  callService();
  return (
      <h1>Home page!</h1>
  );
};

export default HomePage;
