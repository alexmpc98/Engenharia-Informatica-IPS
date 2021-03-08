package pt.pa.adts;

/**
 * Interface que define o comportamento de uma pilha na linguagem Java.
 *
 * Uma pilha é um contentor de elementos que garante uma política de acesso LIFO.
 *
 * Implementações deverão implementar esta interface.
 *
 * @param <T> tipo de elemento a armazenar na pilha.
 */
public interface Stack<T> extends Iterable<T> {

    /**
     * Insere o elemento <i>element</i> no topo da pilha.
     *
     * @param element elemento a inserir.
     *
     * @throws FullStackException se não houver capacidade/memória para mais elementos.
     */
    public void push(T element) throws FullStackException;

    /**
     * Remove o elemento no topo da pilha.
     *
     * @return o elemento no topo da pilha.
     *
     * @throws EmptyStackException se a pilha estiver vazia.
     */
    public T pop() throws EmptyStackException;

    //TODO: EX TPC. completar javadoc

    /**
     *
     * @return
     * @throws EmptyStackException
     */
    public T peek() throws EmptyStackException;

    /**
     *
     * @return
     */
    public int size();
    public boolean isEmpty();
    public void clear();
}