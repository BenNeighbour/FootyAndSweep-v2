import grpcWeb from "grpc-web";

export const handleErrors = (error: grpcWeb.Error) => {
    if (error.code === 16) {
        /* Log the user out, then redirect to login page */
    }
}