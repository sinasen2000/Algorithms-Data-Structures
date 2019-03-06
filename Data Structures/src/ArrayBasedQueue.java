// FIFO principle

public class ArrayBasedQueue<E> {
    private E[] queue;
    private int front = 0; // index of the front element
    private int size = 0; // current number of elements

    public ArrayBasedQueue(int capacity){
        queue = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(E e){
        if (size == queue.length){
            throw new IllegalStateException("Queu is full. Please create a new queue.");
        }
        int available = (front + size) % queue.length; // holds the next available position for queueing
    queue[available] = e;
    size++;
    }

    public E dequeue(){
        if (isEmpty()){
            return null;
        }
        E temp = queue[front];
        queue[front] = null;
        front = (front+1) % queue.length;
        size--;
        return temp;
    }

    public E first(){
        if (isEmpty()){
            return null;
        }
        return queue[front];
    }

    public static void main(String args[]){
        ArrayBasedQueue<String> queue = new ArrayBasedQueue<>(10);
        queue.enqueue("Hamilton");
        queue.enqueue("Alonso");
        queue.enqueue("Verstappen");
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.first());
    }
}
