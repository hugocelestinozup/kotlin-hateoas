FROM openjdk:11

ENV APP_TARGET target
ENV APP itau-bff-service.jar

RUN mkdir -p /opt
COPY ./${APP_TARGET}/${APP} /opt

CMD java ${JAVA_OPTS} -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}
