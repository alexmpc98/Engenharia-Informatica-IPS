package pt.pa;

import pt.pa.adts.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new QueueLinkedList<>();
        Queue<Integer> queueWithNull = new QueueLinkedListNoNulls<>();

        try {
            queueWithNull.enqueue(null);
        } catch (QueueNullNotAllowedException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (int i = 0; i < 100; i++) {
                queue.enqueue(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            /*
            System.out.println("1 - Size before - " + queue.size());
            queue.clear();
            System.out.println("2 - Size after - " + queue.size()); */

            System.out.println("3 - Queue is empty? " + queue.isEmpty());

            System.out.println("4 - Front of queue is: " + queue.front());

            System.out.println("5 - Pop all elements from queue:");
            while (!queue.isEmpty()) {
                System.out.println("6 - Dequeue - " + queue.dequeue());
            }
            System.out.println("7 - Stack is empty? " + queue.isEmpty());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
