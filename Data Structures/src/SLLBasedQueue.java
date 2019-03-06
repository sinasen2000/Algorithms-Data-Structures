public class SLLBasedQueue<E> {
    SinglyLinkedList<E> listQueue = new SinglyLinkedList<>();
    public SLLBasedQueue(){

    }
    public int size(){
        return listQueue.getSize();
    }
    public boolean isEmpty(){
        return listQueue.isEmpty();
    }
    public void enqueue(E e){
        listQueue.addLast(e);
    }
    public E dequeue(){
        return listQueue.removeFirst();
    }
    public E first(){
        return listQueue.first();
    }
    public static void main(String args[]){
        SLLBasedQueue<String> queue = new SLLBasedQueue<>();
        queue.enqueue("Hamilton");
        queue.enqueue("Alonso");
        queue.enqueue("Verstappen");
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.first());
}
}
