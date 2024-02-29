# DIO

### Desafio de Projeto:
- **Explorando Padrões de Projetos na Prática com Java**

Projeto consiste em criar um Cliente com Endereço, este endereço é consultado na API da ViaCEP.<br>
Utilizando o Spring Cloud OpenFeign que de forma declarativa por interface realiza requisições REST Client. 

Design Patterns:
- Singleton
- Strategy
- Facade

Algumas ressalvas:
- No meu caso precisei configurar o H2 Database no arquivo application.properties
  - Deve-se por ter utilizado Java 17 e Spring Boot v3.2.3
- Fiz de uma maneira diferente o método atualizar para aproveitar o id informado no @PathVariable
com isso não precisei informar o id no body da requisição JSON.
