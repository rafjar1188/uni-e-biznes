FROM ubuntu:20.04

# Configure environment variables 

ENV TZ=Europe/Warsaw
ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone 

RUN apt-get update \
    && apt-get install -y apt-utils sudo \
    && apt-get upgrade -y

RUN useradd --create-home rafjar1188 \
    && usermod -aG sudo rafjar1188 \
    && passwd -d rafjar1188

# Changing user

USER rafjar1188

# Changing current working directory

WORKDIR /home/rafjar1188/

RUN mkdir /home/rafjar1188/e-biznes

VOLUME ["/home/rafjar1188/e-biznes"]

SHELL ["/bin/bash", "-c"]

# Install basic software

RUN sudo apt-get install -y wget curl zip unzip micro

# Install SDKMAN

RUN curl -s "https://get.sdkman.io" | bash 
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install java 17.0.2-open \
    && sdk install kotlin 1.6.10 \
    && sdk install gradle 7.4.1 \
    && curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.45.1/ktlint && chmod a+x ktlint \
    && sudo mv ktlint /usr/local/bin/

# ENV PATH=/home/rafjar1188/.sdkman/candidates/java/current/bin:$PATH
# ENV PATH=/home/rafjar1188/.sdkman/candidates/gradle/current/bin:$PATH

# Expose ports

EXPOSE 3000
EXPOSE 9000

# Install Java 17

# RUN apt-get install -y openjdk-17-jre \
#     openjdk-17-jdk \
#     openjdk-17-doc

# ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
# ENV PATH=$PATH:$JAVA_HOME/bin

# Install Kotlin

# RUN wget https://github.com/JetBrains/kotlin/releases/download/v1.6.10/kotlin-compiler-1.6.10.zip \
#     && unzip kotlin-compiler-1.6.10.zip \ 
#     && rm kotlin-compiler-1.6.10.zip \
#     && mv kotlinc kotlin-compiler-1.6.10 \
#     && mv kotlin-compiler-1.6.10 /usr/lib

# ENV KOTLIN_HOME=/usr/lib/kotlin-compiler-bin.1.6.10
# ENV PATH=$PATH:$KOTLIN_HOME/bin
