FROM openjdk:8-jre

ENTRYPOINT ["/entrypoint.sh"]

ADD src/main/docker/entrypoint.sh /

RUN apt-get update \
        && apt-get install -y --no-install-recommends --no-install-suggests zip unzip \
        && rm -rf /var/lib/apt/lists/*

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    TZ=Europe/Kiev \
	LANG=en_US.UTF-8 \
	LANGUAGE=en_US:en \
	TERM=xterm \
    XMX=256m \
    JAVA_OPTS="-Xms128m -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.rmi.port=19999 -Dcom.sun.management.jmxremote.port=19999"

# add directly the war
ADD build/libs/*.war /app.war

EXPOSE 8716