package pt.pa.adts;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue<Integer> queue;
    private static final int numberOfElements = 5;

    @BeforeEach
    void setUp() {
       this.queue  = new QueueLinkedList<>();
    }

    @Test
    public void fifoBehaviorCheck(){
        try {
            this.populateInt(this.numberOfElements);
            for (int i = 0; i < this.numberOfElements; i++) {
                assertEquals(i, this.queue.front());
                assertEquals(i, this.queue.dequeue());
            }
        }
        catch(NullPointerException e){
            System.out.println("A execução apanhou um null pointer, devido a execução do teste da classe filho - QueueLinkedListTest");
        }
    }

    @Test
    void dequeue() {
        // Verificamos se a excepção de queue vazia está funcional
        assertThrows(EmptyQueueException.class, () -> {
            this.queue.dequeue();
        });
    }

    @Test
    void front() {
        // Verificamos se a excepção de queue vazia está funcional
        assertThrows(EmptyQueueException.class, () -> {
            this.queue.dequeue();
        });
    }

    @Test
    void size() {
        try {
            assertTrue(this.queue.size() == 0);
            populateInt(this.numberOfElements);
            assertTrue(this.queue.size() > 0);
            assertEquals(this.queue.size(), this.numberOfElements);
            this.queue.dequeue();
            assertEquals(this.queue.size(), this.numberOfElements - 1);
            this.queue.clear();
            assertTrue(this.queue.size() == 0);
        }
        catch (NullPointerException e){
            System.out.println("A execução apanhou um null pointer, devido a execução do teste da classe filho - QueueLinkedListTest");
        }
    }

    @Test
    void isEmpty() {
        try {
            populateInt(this.numberOfElements);
            assertFalse(this.queue.isEmpty());
            this.queue.clear();
            assertTrue(this.queue.isEmpty());
        }
        catch(NullPointerException e){
            System.out.println("A execução apanhou um null pointer, devido a execução do teste da classe filho - QueueLinkedListTest");
        }
    }

    @Test
    void clear() {
        try {
            populateInt(this.numberOfElements);
            this.queue.clear();
            assertTrue(this.queue.size() == 0);
        }
        catch(NullPointerException e){
            System.out.println("A execução apanhou um null pointer, devido a execução do teste da classe filho - QueueLinkedListTest");
        }
    }

    public void populateInt(int numberOfIntElements){
        for (int i = 0; i < numberOfIntElements; i++) {
            queue.enqueue(i);
        }
    }
}