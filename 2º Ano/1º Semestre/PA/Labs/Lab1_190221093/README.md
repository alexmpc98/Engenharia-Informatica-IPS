As operações que deverão ser suportadas sobre uma *queue* `Q` são apresentadas de seguida:

Operações principais:

* **enqueue(`e`)** - insere o elemento `e` no final de `Q`; a operação deve resultar em **erro** se não houver capacidade/memória para mais elementos;

* **dequeue()** - remove e devolve o elemento que se encontra atualmente no início de `Q`; a operação deve resultar em *erro* se `Q` estiver vazia.

* **front()** - devolve, sem remover, o elemento que se encontra atualmente no início de `Q`; a operação deve resultar em *erro* se `Q` estiver vazia.

Operações genéricas de coleções:

* **size()** - devolve a contagem do número de elementos atualmente em `Q`.

* **isEmpty()** - devolve um valor lógico que indica se `Q` está vazia, ou não.

* **clear()** - descarta todos os elementos presentes em `Q` voltando ao estado de *vazia*.

# Nível Básico (Acompanhamento em Aula)


## Definição de ADT

- Defina a interface `Queue<T>` que descreve o comportamento de uma fila na linguagem Java, de acordo com a especificação fornecida, que armazena elementos do tipo `T`.

- Forneça a documentação *Javadoc* para esta interface, adicionando os comentários de classe/interface e dos métodos; identifique os autores com as *tags* apropriadas. 

## Implementação de ADT

- Forneça uma implementação de `Queue<T>`, baseada em *lista ligada*, na classe `QueueLinkedList`, optando por uma das abordagens da Figura seguinte.


![Implementação do ADT Queue baseada em array e possíveis abordagens com respetivas complexidades para as operações principais. A - inserção (enqueue) no final da lista e remoção (dequeue) do início da lista; B - inserção (enqueue) no início da lista e remoção (dequeue) do final da lista.](adt-queue-impl-linked.png)


- Adicione os comentários *Javadoc* à classe, detalhando a sua implementação e complexidades algorítmicas das operações, ao construtor da classe, aos seus atributos e classe interna.

- No método **main()** implemente um pequeno programa que ilustre o correto comportamento *FIFO* da implementação efetuada e dos restantes métodos.

## Unit Testing

- Por forma a testar objetivamente implementações de `Queue`, deverá desenvolver um conjunto de testes unitários para verificar a correta implementação da classe `QueueLinkedList`, nomeadamente se:

    - O príncipio FIFO é garantido na invocação dos métodos `enqueue`, `dequeue` e `front`;

    - As exceções são corretamente lançadas nos métodos `dequeue` e `front`, nas condições previstas.

> Utilize instância(s) de `QueueLinkedList<Integer>` no desenvolvimento dos testes.

- Verifique que a sua implementação passa nos testes, corrigindo a implementação se necessário.

----

# Nível Básico (Avaliação)


**Apenas disponíveis durante os laboratórios presenciais**. Consistirá num pequeno conjunto de desafios adicionais a resolver individualmente. 

----

# Nível Avançado (Autónomo)


Este nível é para quem pretende fazer avançar os seus conhecimentos na matéria, podendo fazer uso dos horários de dúvidas para acompanhamento.

## ADT Priority Queue

O **ADT Priority Queue** representa uma fila onde os elementos armazenados têm uma *prioridade* associada. Isto significa que ao remover elementos de uma fila com prioridade, os elementos de maior "prioridade" serão removidos primeiro; elementos com a mesma prioridade respeitam o príncipio FIFO.

Na linguagem Java, a forma mais elegante de especificar o critério de prioridade é através do método `compareTo(...)` da *interface* `Comparable<T>`, pelo que o *ADT Priority Queue* só poderá ser instanciado para conter elementos que implementam esta interface, i.e., `public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {...}`

> Note que a interface `Queue` mantém-se inalterada.

A diferença na implementação relativamente a uma fila tradicional (quer baseada em *array* ou *lista ligada*) virá da abordagem que adoptar, sendo que as possíveis são:

**Abordagem A** (privilegia eficiência na inserção):
: -- Um elemento é inserido na estrutura de dados de forma "normal", e.g., no final; mas ao retirar um elemento terá de devolver o elemento com maior prioridade que se encontra na fila há mais tempo.

**Abordagem B** (privilegia eficiência na remoção):
: -- Um elemento é adicionado  na estrutura de dados na sua posição de "prioridade correta", i.e., ele terá de ficar à frente de todos os elementos existentes com menos prioridade; ao remover da fila, simplesmente remove-se o elemento que está na frente. 

**Abordagem C** (menos eficiente):
: -- Um elemento é adicionado na estrutura de dados e os elementos existentes são imediatamente ordenados por prioridade, mas terá de ter em atenção o algoritmo utilizado por forma a respeitar o príncipio FIFO para os elementos com a mesma prioridade; ao remover da fila, simplesmente remove-se o elemento que está na frente. 

> Adicionalmente, a operação `front` também terá de ser adaptada à abordagem escolhida. 

Se pensar um pouco, em nenhuma destas abordagens irá conseguir obter complexidade simultânea de $O(1)$ para ambas as operações `enqueue` e `dequeue`; não existe nenhuma estrutura de dados capaz de permitir tal "proeza" para a fila com prioridade. 

- Proceda à implementação do ADT Priority Queue numa classe `PriorityQueue` utilizando uma abordagem à sua escolha.

    - Se for baseada em *array* o construtor da classe deve inicializar o array com uma capacidade por omissão especificada numa constante privada da classe; a implementação deverá aumentar dinamicamente o array sempre que necessário.

- Forneça a documentação *Javadoc* para a sua classe detalhando o seu funcionamento interno e as complexidades algorítmicas resultantes da sua implementação para os métodos `enqueue`, `dequeue` e `front`.


## Unit Testing

- Implemente a classe `PrintJob` com a assinatura `PrintJob implements Comparable<PrintJob>`, contendo os atributos *name:String*, *priority:Enum{LOW,NORMAL,HIGH}* e *numberPages:int*.

    - Implemente o método `compareTo` devolvendo um valor que espelhe a prioridade relativa mediante o atributo `priority`.

- Por forma a testar objetivamente o nível anterior, deverá desenvolver um conjunto de testes unitários para verificar a correta implementação da classe `PriorityQueue`, nomeadamente se:

    - O príncipio de prioridade/FIFO é garantido na invocação dos métodos `enqueue`, `dequeue` e `front`;

    - Os métodos `size` e `isEmpty` devolvem valores corretos à medida que são adicionados e removidos elementos.

    - As exceções são corretamente lançadas nos métodos `dequeue` e `front`, nas condições previstas.

> Utilize instância(s) de `PriorityQueue<PrintJob>` no desenvolvimento dos testes.
>
> Pode/deve reaproveitar código dos testes do nível anterior.

- Verifique que a sua implementação passa nos testes, corrigindo a implementação se necessário. 