package pt.pa.adts;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Implementação de pilha baseada em array list
 *
 * Os elementos são mantidos sequencialmente num array
 *
 * @param <T> tipo de elementos a armazenar
 */

public class StackArrayList<T> implements Stack<T> {

    private static final int DEFAULT_CAPACITY = 40;

    /** Array onde serão armazenados os elementos */
    private T[] elements;

    /** Contador de elementos da pilha */
    private int size;

    @SuppressWarnings("unchecked")
    public StackArrayList() {
        this.elements = (T[])new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(T element) throws FullStackException {
        if(this.size >= this.elements.length){
            try{
                int newCapacity = (int)(this.elements.length * 1.25);
                T[] newElements = (T[])new Object[newCapacity];
                System.arraycopy(this.elements, 0, newElements, 0, this.size);
                this.elements = newElements;
            } catch (OutOfMemoryError e){
                throw new FullStackException("No memory for more elements");
            }
        }
        this.elements[this.size++] = element;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();
        T elem = this.elements[this.size - 1];
        this.elements[this.size - 1] = null; //Q: Strictly necessary? Why is it a good idea?
        this.size--;
        return elem;
    }

    @Override
    public T peek() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();
        T elem = this.elements[this.size - 1];
        return elem;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public void clear() {
        this.size = 0;
    }
}
