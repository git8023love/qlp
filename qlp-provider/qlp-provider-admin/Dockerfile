FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 779043593@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /qlp-provider-admin

WORKDIR /qlpx-upms

EXPOSE 4000

ADD ./qlp-provider/qlp-provider-admin/target/qlp-provider-admin.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar qlp-provider-admin.jar
