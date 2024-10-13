# Use Ubuntu como imagem base
FROM ubuntu:22.04

# Defina variáveis de ambiente
ENV GRAALVM_VERSION=22.3.0
ENV GRAALVM_HOME=/opt/graalvm
ENV JAVA_HOME=${GRAALVM_HOME}
ENV PATH=${GRAALVM_HOME}/bin:${PATH}

# Atualize o sistema e instale dependências essenciais, incluindo gcc e build-essential
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    maven \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Baixe e instale GraalVM
RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/graalvm-ce-java17-linux-amd64-${GRAALVM_VERSION}.tar.gz \
    && tar -xzf graalvm-ce-java17-linux-amd64-${GRAALVM_VERSION}.tar.gz -C /opt/ \
    && rm graalvm-ce-java17-linux-amd64-${GRAALVM_VERSION}.tar.gz \
    && ln -s /opt/graalvm-ce-java17-${GRAALVM_VERSION} /opt/graalvm

# Instale o componente native-image do GraalVM
RUN gu install native-image

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie o restante dos arquivos do projeto
COPY src ./src

# Compile a native image utilizando o perfil 'native'
RUN mvn clean package -Pnative
