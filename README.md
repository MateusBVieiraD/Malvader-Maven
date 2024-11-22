# Bem Vindo ao Banco Malvader

---

Este trabalho se baseia no desenvolvimento de uma aplicação (um banco) feita em Java, com a ferramente de dependências Maven da Apache e a utilização da JPA com o framework Hibernate. A utilização do Maven facilita a utilização de múltiplas dependências em um arquivo chamado `pom.xml`,
pois este trabalho foi feito em grupo e realizar o carregamento das dependências por cada integrante é simplificado. O Hibernate foi utilizado para realizar a persistência dos dados dentro do banco de dados,
devido a um arquivo chamado`persistence.xml`, carregando o mapeamento das entidades a fim de representar as entidades como tabelas no banco e realizar a persistência. 
O banco Malvader é responsável por fazer operações bancárias, criações de contas e entre outros elementos realizados por outras instituições bancárias.

## Principais Grupos De Classes

- Devido a utilização do Hibernate, as classes foram representadas por tabelas no banco, foram moldadas como `entity`;
- Foram utilizadas classes abstratas chamadas de `modelo` para passarmos as heranças;
- Fizemos a utilização de `dao`'s para termos acesso das classes ao banco de dados;
- Utilizamos classes `controller` para manipularmos os métodos das classes `dao`;
- Para melhorar a performance do código criamos a pasta `config`, onde criamos a classe `EntityFactory`;
- A pasta `bancoController` é utilizada para armazenar as classes contendo a lógica para a exportação dos arquivos CSV;
- A criação da `view` é para moldarmos a interface do projeto.

### EntityFactory
É responsavel pelo armazenamento dos metadados do arquivo `persistence.xml` e a conexão com o banco.

