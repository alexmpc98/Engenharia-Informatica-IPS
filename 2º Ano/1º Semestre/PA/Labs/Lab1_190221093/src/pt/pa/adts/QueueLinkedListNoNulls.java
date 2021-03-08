package pt.pa.adts;

public class QueueLinkedListNoNulls<T> extends QueueLinkedList<T>{

    @Override
    public void enqueue(T element){
        if(element == null){
            throw new QueueNullNotAllowedException();
        }
        else{
            super.enqueue(element);
        }
    }
}
