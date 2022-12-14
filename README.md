<h1 align= "center"> Clínica Odontológica </h1>

[![skills](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot/)
[![skills](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/en/java/)


![Forks](https://img.shields.io/github/forks/ViniciusOcker/IntegradoraFinal)
![Stars](	https://img.shields.io/github/stars/ViniciusOcker/IntegradoraFinal)
![license](https://img.shields.io/github/license/ViniciusOcker/IntegradoraFinal)



# 📁 Projeto | Objetivo :star:

Para Prática, Aplicação e Avaliação dos conhecimentos em BACK-END-CTD em Java.

Criação de um Sistema que permita administrar a reserva e marcação de consultas para uma ou qualquer Instituição Odonlogica.
Esse Sistema será criado com o uso de Java, SpringBoot e com Banco de Dados.


:construction: Projeto em construção :construction:

## ✔️ Requisitos: 

- `Administração de dados Odontológicos`
- `Administração de Pacientes`
- `Login`
- `Registrar consulta`
- `API RESTFul`

## ✔️ Tarefas:

- `Criação de Testes Unitários`
- `Integração com APIs`
- `Login com JWT e SpringSecurity`
- `Documentação com Swagger`
- `Integração com MySQL`

## 🛠️ Técnicas e Tecnologias 

- ``Java``
- ``InteliJ IDEA``
- ``POO``
- ``SpringBoot``
- ``Spring Data JPA``
- ``Spring Security JWT``
- ``RESTFul API``
- ``RESTFul API``
- ``SWAGGER``

## 👨‍🎓 Autores | Grupo 7

| [<img src="https://github.com/vinizer4.png" width=115><br><sub>Vinicius Teixeira Saraiva</sub>](https://github.com/vinizer4) |  [<img src="https://github.com/Wallaceadm.png" width=115><br><sub>Wallace Assis</sub>](https://github.com/Wallaceadm) |  [<img src="https://github.com/Jirayakbc.png" width=115><br><sub>Felipe Roberto Rocha</sub>](https://github.com/Jirayakbc) | [<img src="https://github.com/TKBlade.png" width=115><br><sub>Thiago Brito</sub>](https://github.com/TKBlade) | [<img src="https://github.com/ricfreittas.png" width=115><br><sub>Ricardo de Freitas</sub>](https://github.com/ricfreittas)
| :---: | :---: | :---: | :---: | :---: | 

<br>

## :open_book: Diagrama UML

![exported_from_idea drawio (2)](https://user-images.githubusercontent.com/74463053/207471030-93ad0783-8ddd-475e-a7b2-4b3eac16e1e1.png)

## :books: Estrutura do Projeto

scr/main/java/io.github.vinizer4/

    exception - Implementação de Exceptions para as regras de negócio e controlers

    security.jwt - Implementação do Spring Security

    service - Implementação de Interfaces JPA com a lógica da aplicação

scr/main/java/io.github.vinizer4/rest

     controler - ControllerRest da Aplicação

     dto - Mapeamento banco de dados e camadas API

scr/main/java/io.github.vinizer4/domain

     entity - Interfaces do Spring JPA mapeadas com as Entidades do DB

     repository - Classes de mapeamento entre entidades de DTO


## :label: Endpoints

<img width="211" alt="Screenshot_1" src="https://user-images.githubusercontent.com/74463053/207471677-88ecfd16-2131-4b1e-88ab-12bd8196bafb.png">

<br>

## 🛠️ Exemplo de configuração

Inserir o Username e Password para o banco de dados em scr/main/resources/application.properties para gerar o BD

Ao iniciar o projeto swagger pode ser acessado em localhost:8080/swagger-ui.html#/

<img width="319" alt="Screenshot_7" src="https://user-images.githubusercontent.com/74463053/207482780-ab9933d0-379e-4daf-94df-0866f8c52111.png">

--------------

##### Cadastro de usuário admin

<img width="450" alt="Screenshot_4" src="https://user-images.githubusercontent.com/74463053/207485431-ea3dd58c-0d7d-4499-a74b-8fa5fb32ce9a.png">

<img width="450" alt="Screenshot_5" src="https://user-images.githubusercontent.com/74463053/207485448-316781b4-01dc-4db4-bf3f-8d233ab335fd.png">

--------------

##### Requisitando token de segurança

<img width="450" alt="Screenshot_12" src="https://user-images.githubusercontent.com/74463053/207483678-8a923a2e-0f2a-4155-b166-31856ea02694.png">

--------------

##### Cadastrando Dentista

<img width="450" alt="Screenshot_22" src="https://user-images.githubusercontent.com/74463053/207484367-f5d992e0-2b64-4fc4-8041-3ba1dc7ffd42.png">

<img width="450" alt="Screenshot_23" src="https://user-images.githubusercontent.com/74463053/207484376-a834003c-9be0-4ed3-95b9-55bae8193cc9.png">




