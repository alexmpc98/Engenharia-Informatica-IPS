package pt.pa.adts;

/**
 * Interface que define o comportamento de uma fila na linguagem Java
 *
 * @param <T> Tipo de elementos a armazenar na pilha
 */

public interface Queue<T> {

    /**
     * Insere o elemento <i>element</i> no <i>final</i> (tail) da fila (Queue)
     * @param element Elemento a inserir no fim da fila
     * @throws FullQueueException se não existir capacidade ou memória para mais elementos
     */
    public void enqueue(T element) throws FullQueueException;

    /**
     * Remove o elemento <i>element</i>que se encontra na <i>frente</i> (ou inicio) da fila (front), retornando-o
     * @return Elemento removido da frente (ou inicio) da fila
     * @throws EmptyQueueException se não existir qualquer elemento na fila
     */
    public T dequeue() throws EmptyQueueException;

    /**
     * Devolve, sem remover, o elemento que se encontra atualmente na <i>frente</i> (ou inicio) da fila (front)
     * @return Elemento que se encontra na frente da fila
     * @throws EmptyQueueException se não existir qualquer elemento na fila
     */
    public T front() throws EmptyQueueException;

    /**
     * Devolve a contagem do número de elementos atualmente na queue
     * @return Contagem do número de elementos atualmente na queue
     */
    public int size();

    /**
     * Devolve um valor lógico que indica se a Queue está vazia, ou não
     * @return Verdadeiro se a fila está vazia, falso se não está vazia
     */
    public boolean isEmpty();

    /**
     * Descarta todos os elementos presentes na Queue
     */
    public void clear();

}
