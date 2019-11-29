FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-codegen

WORKDIR /qlp-codegen

EXPOSE 5003

ADD ./qlp-visual/qlp-codegen/target/qlp-codegen.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-codegen.jar
