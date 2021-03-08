package pt.pa.adts;

/**
 * Interface que define o comportamento de uma pilha na linguagem JAVA
 * @param <T> Tipo de elementos a armazenar na pilha
 */

public interface Stack<T>  {

    /**
     * Insere um novo elemento <i>element</i> no topo da pilha
     * @param element elemento a inserir na pilha
     * @throws FullStackException se não existir capacidade ou memória para mais elementos
     */
    public void push(T element) throws FullStackException;

    /**
     * Remove um elemento <i>element</i> do topo da pilha e retorna-o
     * @return Elemento no topo da pilha
     * @throws EmptyStackException se não existir qualquer elemento na pilha
     */
    public T pop() throws EmptyStackException;

    /**
     * Retorna o elemento <i>element</i> do topo da pilha, sem o remover
     * @return Elemento no topo da pilha
     * @throws EmptyStackException se não existir qualquer elemento na pilha
     */
    public T peek() throws EmptyStackException;

    /**
     * Retorna ao utilizador, o tamanho da pilha
     * @return Tamanho da pilha
     */
    public int size();

    /**
     * Retorna ao utilizador, a informação sobre se a pilha está vazia
     * @return Verdadeiro se a pilha está vazia, falso se possui pelo menos um elemento
     */
    public boolean isEmpty();

    /**
     * Limpa o contéudo da pilha
     */
    public void clear();
}