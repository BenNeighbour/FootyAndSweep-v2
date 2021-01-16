
export const interceptGrpcResponse = (response: any) => {
    if (response.code === 16) {
        window.location.replace("http://api.footyandsweep-dev.com:30077/oauth2/authorize/google?redirect_uri=http://www.footyandsweep-dev.com:3000/home")
    }
}