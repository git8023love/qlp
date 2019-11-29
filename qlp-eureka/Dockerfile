FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

# 设置时间为东八区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-eureka

WORKDIR /qlp-eureka

EXPOSE 8761

ADD ./qlp-eureka/target/qlp-eureka.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-eureka.jar
