#Estrutura de dados
Foi utilizada a estrutura de dados (lista de adjacências), pois é de mais fácil manipulação e possui uma complexidade algorítmica geral inferior à matriz de adjacências

#Especificação de algoritmos 
Para o carregamento de dados e construção do modelo, deve-se:
* Criar uma instância de SocialNetwork;
```java
SocialNetwork socialNetwork = new SocialNetwork();
```
* Executar o método abaixo, para realizar a leitura do ficheiro e guardar os dados;
```java
socialNetwork.readCsvFile("relationships");
```
 * Executar o método abaixo, para criar os vertices com os dados lidos do ficheiro;
```java
socialNetwork.insertUsers();
```
* Executar o método abaixo, para criar as arestas com os dados lidos do ficheiro;
```java
socialNetwork.insertRelations();
```
