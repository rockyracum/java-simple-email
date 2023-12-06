![Jersey Framework](https://upload.wikimedia.org/wikipedia/commons/7/71/Glassfish_Jersey.png)

# API de e-mail simples

_java-simple-email_ é uma __API REST__ criada com a linguagem de programação Java e o framework Jersey, desenvolvido a partir da necessidade de enviar e-mails de forma simplificada.

[![License](https://img.shields.io/npm/l/react)](https://raw.githubusercontent.com/ordanael/java-simple-email/main/LICENSE) 

## Tecnologias

- Java
- Jersey Framework
- Google reCAPTCHA
- Sendinblue
- Docker

## Funcionalidades

- Envio de emails via Sendinblue
- Google reCAPTCHA para evitar spams

## Execução

> Requisitos: Chave Google reCaptcha v2 e SendInBlue

### Com Docker

> Requisitos: Docker

```shell
# Baixar a imagem do Docker Hub
docker pull ordanael/java-simple-email:latest

# Criar um container a partir da imagem e expôr a porta 8080
# Substitua VALUE por suas respectivas chaves
docker run -t -i -p 8080:8080 --name java-simple-email -e RECAPTCHA_PRIVATE_KEY=VALUE -e SENDINBLUE_API_KEY=VALUE -e EMAIL_SENDER=VALUE -e EMAIL_SUBJECT="VALUE" ordanael/java-simple-email:latest

# A aplicação já está em execução neste ponto, utilize os comandos abaixo para desligar a aplicação

# Parar o container
docker stop java-simple-email

# Iniciar um container que está parado
docker start java-simple-email

# Remover container
docker rm java-simple-email

# Remover imagem
docker rmi ordanael/java-simple-email:latest
```

### Com Git

> Requisitos: Git, Java17 e Apache Maven

```shell
# Clonar o repositório git
git clone https://github.com/ordanael/java-simple-email.git

# Entrar na pasta do repositório
cd java-simple-email

# Instalar pacotes
mvn install

# Compilar código fonte
mvn package

# Inicializar API
# Substitua VALUE por suas respectivas chaves
mvn exec:java -DRECAPTCHA_PRIVATE_KEY=VALUE -DSENDINBLUE_API_KEY=VALUE -DEMAIL_SENDER=VALUE -DEMAIL_SUBJECT="VALUE"
```

# Endpoints

| Método | Rota | Funcionalidade | Payload |
|---|---|---|---|
| GET | / | Testa a conexão com o servidor |  |
| POST | /api/email | Envia o e-mail contendo o nome, e-mail e mensagem do usuário | {"name": "string", "email": "string", "message": "string", "grecaptcha": "string"} |
