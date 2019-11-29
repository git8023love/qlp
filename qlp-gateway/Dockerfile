FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-gateway

WORKDIR /qlp-gateway

EXPOSE 9999

ADD ./qlp-gateway/target/qlp-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-gateway.jar
