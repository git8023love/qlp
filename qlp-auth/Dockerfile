FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-auth

WORKDIR /qlp-auth

EXPOSE 3000

ADD ./qlp-auth/target/qlp-auth.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-auth.jar
