package pt.pa.adts;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
	    //Stack<Integer> stack = new StackArrayList<>();
        Stack<Integer> stack = new StackLinkedList<>();

	    try {
            for (int i=0; i<10; i++) {
                stack.push(i);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

	    for(Integer i : stack) {
            System.out.println(i);
        }

	    // -> traduzido em:
        /*
        Iterator<Integer> iterator = stack.iterator();
	    while(iterator.hasNext()) {
	        Integer i = iterator.next();

            System.out.println(i);
        }
        */

        //stack.clear();

        /*
	    try {
            System.out.println("Stack is empty? " + stack.isEmpty());

            System.out.println("Top of stack is: " + stack.peek());

            System.out.println("Pop all elements from stack:");
            while(!stack.isEmpty()) {
                System.out.println(stack.pop());
            }

            System.out.println("Stack is empty? " + stack.isEmpty());

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        */
    }
}
