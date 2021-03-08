package pt.pa;

import pt.pa.adts.*;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new QueueLinkedList<>();
        Queue<Integer> queueWithNull = new QueueLinkedListNoNulls<>();
        Queue<Integer> PriorityQueue = new PriorityQueue<>();

        /*
        try{
            queueWithNull.enqueue(null);
        } catch(QueueNullNotAllowedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------- Priority Queue ---------------------- \n");
        //PriorityQueue.front();
        PriorityQueue.enqueue(2);
        PriorityQueue.enqueue(3);
        System.out.println("First front - " + PriorityQueue.front() + "\n");
        PriorityQueue.enqueue(4);
        System.out.println("Updated front - " + PriorityQueue.front() + "\n");
        while(!PriorityQueue.isEmpty()){
            System.out.println(" - Priority Dequeue - " + PriorityQueue.dequeue());
        }
        System.out.println("***************************************************************** \n");
*/
        try{
            for(int i=0;i<100;i++){
                queue.enqueue(i);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            /*
            System.out.println("1 - Size before - " + queue.size());
            queue.clear();
            System.out.println("2 - Size after - " + queue.size()); */

            System.out.println("3 - Queue is empty? " + queue.isEmpty());

            System.out.println("4 - Front of queue is: " + queue.front());

            System.out.println("5 - Pop all elements from queue:");
            while(!queue.isEmpty()) {
                System.out.println("6 - Dequeue - " + queue.dequeue());
            }
            System.out.println("7 - Stack is empty? " + queue.isEmpty());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
