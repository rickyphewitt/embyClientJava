# Build Image
FROM ubuntu:16.04
RUN DEBIAN_FRONTEND=noninteractive apt-get update && apt-get install -yqq openjdk-8-jdk maven
#RUN useradd builder -m -s /bin/bash
WORKDIR /home/builder
ADD . /home/builder/
#USER builder
CMD ["mvn", "clean", "install"]