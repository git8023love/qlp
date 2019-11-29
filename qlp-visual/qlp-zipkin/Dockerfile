FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-zipkin

WORKDIR /qlp-zipkin

EXPOSE 5002

ADD ./qlp-visual/qlp-zipkin/target/qlp-zipkin.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-zipkin.jar
