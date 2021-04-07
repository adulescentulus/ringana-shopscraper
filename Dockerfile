FROM ghcr.io/adulescentulus/docker-graalvm-maven:21.0.0.2 as native-image

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn --version

RUN native-image --version

WORKDIR /build

ADD pom.xml /build

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && \
    mvn -Pnative-image --fail-never -B dependency:go-offline test org.graalvm.nativeimage:native-image-maven-plugin:native-image && \
    rm -rf target

ADD src /build/src

ARG MAVEN_PATH
ARG MAVEN_SNAPSHOT_URI
ARG MAVEN_RELEASE_URI
ARG MAVEN_OPTS
ARG MAVEN_CLI_OPTS

#ENV NATIVE_BUILD_ARGS="--static --libc=musl"
ENV NATIVE_BUILD_ARGS=""
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && env && mvn -B $MAVEN_CLI_OPTS -Pnative-image clean package
RUN mkdir /tmp_new

# We use a Docker multi-stage build here in order to only take the compiled native Spring Boot App from the first build container
FROM ubuntu:focal

MAINTAINER Andreas Groll

# Add Spring Boot Native app spring-boot-graal to Container
COPY --from=native-image "/build/target/shopscraper" shopscraper
#COPY --from=native-image "/staticlibs" staticlibs
#COPY --from=native-image "/tmp_new" tmp
#COPY --from=native-image "/tmp_new" /dev/urandom

# Fire up our Spring Boot Native app by default
ENTRYPOINT [ "/shopscraper" ]