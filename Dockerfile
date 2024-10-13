# Usar a imagem base do GraalVM com Maven instalado
FROM ghcr.io/graalvm/graalvm-ce:22.3.0

# Instalar o componente native-image
RUN gu install native-image

# Instalar o Maven (caso não esteja incluído na imagem)
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar o restante dos arquivos do projeto
COPY src ./src

# Compilar a native image usando o perfil 'native'
RUN mvn clean package -Pnative
