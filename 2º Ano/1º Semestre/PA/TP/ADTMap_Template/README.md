ADT Map Template
===

Este repositório consiste num projeto **IntelliJ** 
de suporte à lecionação dos tipos abstratos de dados na linguagem Java,
no contexto da unidade curricular de *Programação Avançada* - ESTSetúbal.

Os exercícios solicitados são os seguintes:

## Exercícios

1. Faça *clone* deste projeto base **ADTMap_Template** (projeto **IntelliJ**) do *GitHub*.

2. Complete o código em falta no `main()`, utilizando as operações de `Map`.

3. Por forma a poder testar a implementação da classe `MapBST` forneça a implementação dos seguintes dois métodos auxiliares:
   
   - `private BSTNode searchNodeWithKey(K key, BSTNode treeRoot)`

       - Dada a raiz de uma (sub-)árvore, pesquisa o nó que contém essa chave; `null` se não existir. **Forneça uma implementação <u>recursiva</u>**.

   - `private BSTNode getLeftmostNode(BSTNode treeRoot)`

       - Dada a raiz de uma (sub-)árvore, pesquisa o seu nó mais à esquerda (*contém a chave "mínima"*); `null` se não existir. **Forneça uma implementação <u>recursiva</u> ou <u>iterativa</u>**.

4. Execute o método `main()` utilizando a implementação completa de `MapBST`;

5. Utilize o método `MapBST.toString()` que irá mostrar uma representação textual da árvore subjacente:

    ```bash
    MapBST of size = 8:
    │           ┌── {key=9, value=3
    │       ┌── {key=8, value=2
    │   ┌── {key=7, value=2
    │   │   └── {key=6, value=4
    └── {key=5, value=2
        │   ┌── {key=4, value=5
        │   │   └── {key=3, value=2
        └── {key=1, value=2
    ```

6. Teste a remoção de mapeamentos, verificando as árvores resultantes.

7. Altere a implementação por forma a que os métodos `keys()` e `values()` utilizem uma *travessia* **em-ordem** da árvore.
  
    - No caso de `keys()`, dado que são as chaves da árvore, a coleção irá conter esses elementos ordenados.
      
 8. Adicione ao *output* do método `toString()` informação sobre a **altura da árvore**, e.g.:
    
    ```bash
    MapBST of size = 8 and height = 3:
    │           ┌── {key=9, value=3
    │       ┌── {key=8, value=2
    │   ┌── {key=7, value=2
    │   │   └── {key=6, value=4
    └── {key=5, value=2
        │   ┌── {key=4, value=5
        │   │   └── {key=3, value=2
        └── {key=1, value=2
    ```
    
    * Implemente/utilize um método auxiliar recursivo:
    
        - `private int height(BSTNode treeRoot)`