/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

/**
 *
 * @author Alexandre, Sergio
 */
public class ValuedElement <E> implements Comparable {
    private E element;
    private int value;
    
    public ValuedElement (E element, int value) {
        this.element = element;
        this.value = value;
    }
    
    public E getElement() {
        return this.element;
    }
    
    public int getValue() {
        return this.value;
    }
    
    @Override
    public int compareTo(Object elem){
        ValuedElement element = (ValuedElement)elem;
        int elementValue = element.getValue();
        if (this.value < elementValue) {
             return -1;
        } else if (this.value > elementValue){
            return 1;
        } 
        return 0;
    } 
}
