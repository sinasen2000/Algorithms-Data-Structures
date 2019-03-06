public class CircularQueue<E> {
    CircularlyLinkedList<E> queue = new CircularlyLinkedList<>();

    public CircularQueue(){

    }
    public int size(){
        return queue.getSize();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public void enqueue(E e){
        queue.addLast(e);
    }
    public E dequeue(){
        return queue.removeFirst();
    }
    public E first(){
        return queue.getFirst();
    }
    public void rotate(){
        queue.rotate();
    }

    public static void main(String args[]){
        CircularQueue<String> queue = new CircularQueue<>();
        queue.enqueue("Kobe");
        queue.enqueue("2Chainz");
        queue.enqueue("Dominic Toretto");
        queue.enqueue("Salt Bae");
        System.out.println(queue.first());
        System.out.println(queue.dequeue());
        System.out.println(queue.first());
        queue.rotate();
        System.out.println(queue.first());


    }
}
