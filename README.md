# API-encripta-password
API-encripta-password é um projeto pessoal que busca compreender os processos e conceitos como também o funcionamento da encriptação de senha no banco de dados e sua decriptação para validar dados. A priori, para este exemplo, o microsserviço foi usado para validação de tela de login, no entanto, nada impede sua aplicabilidade em outros serviços após ajustes no código.

## 🚀 Começando
Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

Consulte **[Implantação](#-implanta%C3%A7%C3%A3o)** para saber como implantar o projeto.

### 📋 Pré-requisitos

```
Java 17
IDE Eclipse
Postman ou Insonmia
```
### 🔧 Instalação
#### Linux
```bash
# navegue até seu diretório de downloads
cd seuDiretório/

# clonando repositório
git clone https://github.com/wandersonlira/API-encripta-password.git

# descompactando arquivo em caso .zip
unzip API-encripta-password.zip
```
#### Windows
```bash
# abra o git bash

# navegue até seu diretório de downloads
cd seuDiretório/

# clonando repositório
git clone https://github.com/wandersonlira/API-encripta-password.git

# descompacte o arquivo em caso .zip
```
## 📦 Implantação

### :whale: Executando via Docker
***Navegue até a pasta do projeto depois execute os seguintes comandos:***
1. ***Gera imagem docker***
```
docker build -t encrypta-password .
```
2. ***Executa o container na porta especificada e atribui nome `encrypta-password`.***
  - *obs: `container` port:8080, `host` port:8082. Significa que a porta 8080 do container houve a porta 8082 do host.*
```
docker run -d --name encrypta-password -p 8082:8080 encrypta-password:latest
```
### Importando no Eclipse
  - `File/Import/Maven/Existing Maven Projects` *depois clique em Next, Browse para pesquisar o arquivo e por fim Finish*

![tela2](https://github.com/wandersonlira/API-encripta-password/assets/128269826/17a4989c-94c7-4418-8337-77154eee2c3f)
![tela3](https://github.com/wandersonlira/API-encripta-password/assets/128269826/9a9bc05c-8265-4155-9a7a-73499602838c)

## 🛠️ Construído com

* [Spring Boot, Spring Security, ](https://spring.io/projects/spring-boot) - O framework usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [H2 Database](https://www.h2database.com/html/main.html) - Banco de dados em memória

## 📌 Versão
Versão 1.0
## 📄 Licença
Este projeto está sob a licença (MIT License) - veja o arquivo [LICENSE.md](https://github.com/wandersonlira/API-encripta-password/blob/main/LICENSE) para detalhes.
## Autor
Wanderson Lira
#### Linkedin
https://www.linkedin.com/in/wandersonlira/

