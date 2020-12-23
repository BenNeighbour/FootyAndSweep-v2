const { GreetingServiceClient } = require('../proto/greeting_service_grpc_web_pb');
const { HelloRequest, HelloResponse } = require('../proto/greeting_service_pb');

export const getTestThing = (action: any) => {
    const client = new GreetingServiceClient('http://localhost:9090', null, null);

    const callService = () => {
        const request = new HelloRequest();
        request.setName("Ben The Neighbour")

        client.greeting(request, {}, (err: any, response: any) => {
            if (response == null) {
                console.log(err);
            } else {
                console.log(response.getGreeting());
            }
        });
    }

    console.log("test");
}
