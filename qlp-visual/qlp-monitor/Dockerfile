FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-monitor

WORKDIR /qlp-monitor

EXPOSE 5001

ADD ./qlp-visual/qlp-monitor/target/qlp-monitor.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-monitor.jar
