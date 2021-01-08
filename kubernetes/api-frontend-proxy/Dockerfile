FROM envoyproxy/envoy-alpine:v1.16-latest

RUN apk --no-cache add ca-certificates

ADD envoy.yaml ./etc/

CMD ["/usr/local/bin/envoy", "-c", "/etc/envoy.yaml", "-l", "debug"]
