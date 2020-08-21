FROM openjdk:11-jre-slim
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Duser.timezone=gmt","-cp","app:app/lib/*","com.bmx.verde.RddbWritebackApplication"]
