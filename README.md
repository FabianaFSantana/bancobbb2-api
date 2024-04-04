# bancobbb2-api
Api Rest para simulção de um sistema bancário.
<div align="center">
  
![BancoBBB](https://github.com/FabianaFSantana/bancobbb2-api/assets/161942930/a1122719-54b8-4b6a-951f-d146a2db463b)

</div>

## Descrição do Projeto
O Bancobbb é uma API REST desenvolvida com Spring Boot para servir como backend de uma aplicação de simulação de um sistema bancário. Ele oferece recursos para manipulação de usuários, funcionários, contas correntes, contas poupanças e contas salários, proporcionando uma interface para interação com o banco de dados MySQL.

## Configuração do Ambiente

### Requisitos
Certifique-se de ter as seguintes dependências instaladas em seu ambiente de desenvolvimento:

* [Java 11+](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL](https://dev.mysql.com/downloads/installer/)

### Instalação
1. Clone o repositório:
```
git clone https://github.com/alexandresjunior/taskify-api.git](https://github.com/FabianaFSantana/bancobbb2-api.git)
```
2. No terminal, navegue até o diretório do projeto:
```
cd bancobbb2-api
```
3. Configure o banco de dados:
Certifique-se de que um servidor MySQL esteja em execução e crie um banco de dados chamado "bancobbb2".
```
CREATE DATABASE bancobbb2;
```
4. Configure as propriedades do banco de dados:
Se for o caso, edite o arquivo `src/main/resources/application.properties` e ajuste as configurações de conexão com o servidor MySQL:
```
spring.datasource.url=jdbc:mysql://localhost:3306/bancobbb2
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```
5. Execute a aplicação:
```
mvn spring-boot:run
```
O servidor estará acessível em `http://localhost:8080` por padrão.

## Estrutura do Projeto
O projeto é estruturado da seguinte forma:
* `com.bancobbb2.api.dto`: Dto's para receber valores double de depósito e saque via Json.
* `com.bancobbb2.api.controller`: Controladores para manipular as requisições HTTP.
* `com.bancobbb2.api.model`: Modelos de dados para representar Banco, Pessoa (Embeddable), Endereco (Embeddable), Usuários, Funcionarios, ContaCorrente, ContaSalario e ContaPoupanca.
* `com.bancobbb2.api.repository`: Repositórios para interação com o banco de dados.
* `com.bancobbb2.api.service`: Servicos de ContaCorrente, ContaPoupanca e ContaSalario para criar os métodos para relacionar contas ao usuário e ao funcionário, sacar, depositar, calcular rendimento e exibir saldo.

## Uso da API
A API possui os seguintes endpoints:

### Bancos:
* `POST /banco`: Cria um novo usuário.
* `POST /banco/{idBanco}/adicionarFuncionario/{idFuncionario}`: Adciona um funcionário cadastrado a uma lista de funcionários do banco.
* `POST /banco/{idBanco}/adicionarUsuario/{idUsuario}`: Adciona um usuário cadastrado a uma lista de usuários do banco.
* `GET /banco`: Lista todos os bancos.
* `GET /banco/{id}`: Obtém informações de um banco específico.
* `PUT /banco/{id}`: Atualiza as informações de um banco.
* `DELETE /banco/{id}`: Exclui um banco.

### Usuários:
* `POST /usuario`: Cria um novo usuário.
* `POST /usuario/{idUsuario}/associarUsuarioContaCorrente/{idCc}`: Relaciona usuário à conta corrente.
* `POST /usuario/{idUsuario}/associarUsuarioContaPoupanca/{idCp}`: Relaciona usuário à conta poupança.
* `POST /usuario/{idUsuario}/associarUsuarioContaSalario/{idCs}`: Relaciona usuário à conta salário.
* `GET /usuario`: Lista todos os usuários.
* `GET /usuario/{idUsuario}`: Obtém informações de um usuário específico.
* `GET /usuario/cpf/{cpf}`: Obtém informações de um usuário específico pelo cpf.
* `PUT /usuario/{idUsuario}`: Atualiza as informações de um usuário.
* `DELETE /usuario/{idUsuario}`: Exclui um usuário.

### Funcionários:
* `POST /funcionario`: Cria um novo funcionario.
* `POST /funcionario/{idFuncionario}/associarFuncContaCorr/{idCc}`: Relaciona funcionário à conta corrente.
* `POST /funcionario/{idFuncionario}/associarFuncContaPoup/{idCp}`: Relaciona funcionário à conta poupança.
* `POST /funcionario/{idFuncionario}/associarFuncContaSalario/{idCs}`: Relaciona funcionário à conta salário.
* `GET /funcionario`: Lista todos os funcionarios.
* `GET /funcionario/{idFuncionario}`: Obtém informações de um funcionario específico.
* `GET /funcionario/cpf/{cpf}`: Obtém informações de um funcionario específico pelo cpf.
* `PUT /funcionario/{idfuncionario}`: Atualiza as informações de um funcionario.
* `DELETE /funcionario/{idfuncionario}`: Exclui um funcionario.

### Contas Correntes:
* `POST /contaCorrente`: Cria uma nova conta corrente.
* `POST /contaCorrente/depositarValor/{idCc}`: Deposita um valor ao saldo da conta corrente.
* `POST /contaCorrente/sacarValor/{idCc}`: Saca uma valor da conta corrente.
* `GET /contaCorrente`: Lista todos as contas correntes.
* `GET /contaCorrente/{idCc}`: Obtém informações de uma conta corrente específica.
* `GET /contaCorrente/saldo/{idCc}`: Exibe o saldo da conta corrente.
* `PUT /contaCorrente/{idCc}`: Atualiza as informações de uma conta corrente.
* `DELETE /contaCorrente/{idCc}`: Exclui uma conta corrente.

### Contas Poupanças:
* `POST /contaPoupanca`: Cria uma nova conta Poupança.
* `POST /contaPoupanca/depositarValor/{idCp}`: Deposita um valor ao saldo da conta poupança.
* `POST /contaPoupanca/sacarValor/{idCp}`: Saca uma valor da conta poupança.
* `POST /contaPoupanca/calcularRendimento`: calcula o rendimentos das contas poupanças do banco.
* `GET /contaPoupanca`: Lista todos as contas poupanças.
* `GET /contaPoupanca/{idCp}`: Obtém informações de uma conta poupança específica.
* `GET /contaPoupanca/exibirSaldo/{idCp}}`: Exibe o saldo da conta poupança.
* `PUT /contaPoupanca/{idCp}`: Atualiza as informações de uma conta poupança.
* `DELETE /contaPoupanca/{idCp}`: Exclui uma conta poupança.

### Contas Salários:
* `POST /contaSalario`: Cria uma nova conta salário.
* `POST /contaSalario/depositarValor/{idCs}`: Deposita um valor ao saldo da conta salário.
* `POST /contaSalario/sacarValor/{idCs}`: Saca uma valor da conta salário.
* `GET /contaSalario`: Lista todos as contas salários.
* `GET /contaSalario/{idCs}`: Obtém informações de uma conta salário específica.
* `GET /contaSalario/exibirSaldo/{idCs}}`: Exibe o saldo da conta salário.
* `PUT /contaSalario/{idCs}`: Atualiza as informações de uma conta salárioa.
* `DELETE /contaSalario/{idCs}`: Exclui uma conta salário.

## Chamando os Endpoints via Postman
Após iniciar a aplicação, você pode acessar a documentação interativa da API por meio Postman. Lá, você encontrará uma interface fácil de usar para explorar e testar os endpoints da API.
 

