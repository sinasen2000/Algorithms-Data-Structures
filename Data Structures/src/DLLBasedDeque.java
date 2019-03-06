public class DLLBasedDeque<E> {
    DoublyLinkedList<E> deque = new DoublyLinkedList<>();

    public DLLBasedDeque(){

    }
    public int size(){
        return deque.size();
    }
    public boolean isEmpty(){
        return deque.isEmpty();
    }
    public E first(){
        return deque.first();
    }
    public E last(){
        return deque.last();
    }
    public void addFirst(E e){
        deque.addLast(e);
    }
    public void addLast(E e){
        deque.addFirst(e);
    }
    public E removeFirst(){
        if (isEmpty()){
            return null;
        }
        return deque.removeFirst();
    }
    public E removeLast(){
        if (isEmpty()){
            return null;
        }
        return deque.removeLast();
    }

}
