FROM ghcr.io/adulescentulus/docker-graalvm-maven:21.0.0.2 as native-image

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn --version

RUN native-image --version

ADD . /build
WORKDIR /build

ARG MAVEN_PATH
ARG MAVEN_SNAPSHOT_URI
ARG MAVEN_RELEASE_URI
ARG MAVEN_OPTS
ARG MAVEN_CLI_OPTS

ENV NATIVE_BUILD_ARGS="--static --libc=musl"
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && env && mvn $MAVEN_CLI_OPTS -Pnative-image clean package

# We use a Docker multi-stage build here in order to only take the compiled native Spring Boot App from the first build container
FROM scratch

MAINTAINER Andreas Groll

# Add Spring Boot Native app spring-boot-graal to Container
COPY --from=native-image "/build/target/shopscraper" pull-ids-cron
COPY --from=native-image "/staticlibs" staticlibs

RUN mkdir /tmp

# Fire up our Spring Boot Native app by default
ENTRYPOINT [ "/shopscraper" ]