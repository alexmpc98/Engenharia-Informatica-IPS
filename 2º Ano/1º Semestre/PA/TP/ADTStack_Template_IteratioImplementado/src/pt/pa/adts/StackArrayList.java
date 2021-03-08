package pt.pa.adts;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implementação de pilha ({@link Stack}) baseada em array list.
 *
 * @param <T> elemento a armazenar.
 */
public class StackArrayList<T> implements Stack<T> {

    private static final int DEFAULT_CAPACITY = 40;

    /** array que contém os elementos armazenados */
    private T[] elements;
    /** contador de elementos armazenados */
    private int size;


    @SuppressWarnings("unchecked")
    public StackArrayList() {
        this.elements = (T[])new Object[DEFAULT_CAPACITY];
        this.size = 0;


    }

    @Override
    public void push(T element) throws FullStackException {
        //complexidade algorítmica constante - O(1)
        if(this.size >= this.elements.length) {

            try {
                int newCapacity = this.elements.length * 2;
                T[] newElements = (T[])new Object[newCapacity];
                //1.
                //for(int i=0; i<this.size; i++) {
                //    newElements[i] = this.elements[i];
                //}
                System.arraycopy(this.elements, 0, newElements, 0, this.size);

                this.elements = newElements; //this.elements e newElements são ponteiros para zonas de memória (1º elemento do array)
            } catch(OutOfMemoryError e) {
                throw new FullStackException("No memory for more elements.");
            }

        }

        this.elements[this.size++] = element;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();

        //complexidade algorítmica constante - O(1)
        T elem = this.elements[this.size - 1];
        //this.elements[this.size - 1] = null; //Q: Strictly necessary? No!. Why is it a good idea? Yes, Help garbage collecting.
        this.size--;

        return elem;
    }

    @Override
    public T peek() throws EmptyStackException {
        if(this.size == 0) throw new EmptyStackException();

        //complexidade algorítmica constante - O(1)
        T elem = this.elements[this.size - 1];

        return elem;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        //if(this.size == 0) return true;
        //else return false;

        return (this.size == 0);
    }

    @Override
    public void clear() {
        for(int i=0; i<this.size; i++) {
            this.elements[i] = null; //just to help GC free earlier
        }

        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
        //return new MyIteratorBottomTop();
    }

    private class MyIterator implements Iterator<T> {

        private int currentIndex;

        public MyIterator() {
            currentIndex = size - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public T next() {
            //return elements[currentIndex--];
            T next = elements[currentIndex];

            currentIndex--;

            return next;
        }
    }

    private class MyIteratorBottomTop implements Iterator<T> {

        private int currentIndex;

        public MyIteratorBottomTop() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex <= size - 1;
        }

        @Override
        public T next() {
            //return elements[currentIndex--];
            T next = elements[currentIndex];

            currentIndex++;

            return next;
        }
    }
}
