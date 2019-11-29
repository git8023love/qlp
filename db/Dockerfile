FROM mysql:5.7

MAINTAINER jeff(779043593@qq.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./db/qlp.sql /docker-entrypoint-initdb.d
