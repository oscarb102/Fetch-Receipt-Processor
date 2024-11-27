FROM amazoncorretto:23

# Install wget and gzip
RUN yum install -y wget unzip

# Install maven
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip \
    && unzip apache-maven-3.9.9-bin.zip -d /opt \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn \
    && rm -f apache-maven-3.9.9-bin.zip

# Set the environment variable for Maven
ENV MAVEN_HOME=/opt/apache-maven-3.9.9
ENV PATH=$MAVEN_HOME/bin:$PATH

# Expose the port the app runs on (optional)
EXPOSE 8080

# Copy project
COPY target/receipt-processor-1.0.0.jar app.jar
CMD [ "java", "-jar", "app.jar" ]