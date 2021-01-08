FROM openjdk:8

RUN apt-get update && \
  apt-get install -y curl && \
  rm -rf /var/lib/apt/lists/*

CMD java ${JAVA_OPTS} -jar api-sweepstake-engine-*.jar

COPY build/libs/api-sweepstake-engine-*.jar .