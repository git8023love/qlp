FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-config

WORKDIR /qlp-config

EXPOSE 4001

ADD ./qlp-config/target/qlp-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-config.jar
