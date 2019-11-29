FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-privoder-oa

WORKDIR /qlp-privoder-oa

EXPOSE 5004

ADD ./qlp-privoder/qlp-privoder-oa/target/qlp-privoder-oa.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-privoder-oa.jar
