package pt.pa.adts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class QueueLinkedListTest extends QueueTest{
    private Queue<Integer> queueNull;

    @BeforeEach
    void setUp() {
        this.queueNull = new QueueLinkedListNoNulls<>();
    }

    @Test
    void enqueue() {
        assertThrows(QueueNullNotAllowedException.class, () -> {
            this.queueNull.enqueue(null);
        });
    }
}