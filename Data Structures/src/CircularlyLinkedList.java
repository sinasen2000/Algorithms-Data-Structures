import java.util.NoSuchElementException;

public class CircularlyLinkedList<E> {
    //nested Node class
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    // instance variables of CLL
    private Node<E> tail = null;
    // for optimization, we dont need to explicitly store head as a variable as
    // we can reach head by doing tail.getNext();
    private int size = 0;
    public CircularlyLinkedList(){}

    // access methods

    public int getSize() {
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E getFirst(){
        if (isEmpty()){
            return  null;
        }
        return tail.getNext().getElement();
    }
    public E getLast(){
        if (isEmpty()){
            return  null;
        }
        return tail.getElement();
    }

    // update methods

    public void rotate(){
        if (tail != null){
            tail = tail.getNext();
        }
    }
    public void addFirst(E e){
        if (size == 0){
            tail = new Node<>(e, null);
            tail.setNext(tail);
        }else{
            Node<E> node = new Node<>(e, tail.getNext());
            tail.setNext(node);
        }
        size++;
    }
    public void addLast(E e){
        addFirst(e);
        tail = tail.getNext();
    }
    public E removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node<E> head = tail.getNext();
        if(head == tail){
            return null;
        }else{
            tail.setNext(tail.getNext().getNext()); // can be also done with head.getNext()
        }
        size--;
        return head.getElement();
    }
    public E removeLast(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }// headin nextini de belirle, olmazsa tail = ile tail.setNextin yerini degistir, eline kalem kagit al yap
        E temp = tail.getElement();
        Node head = tail.getNext();
        tail = head;
        tail.setNext(head.getNext());
        size--;
        return temp;
    }//refactor
    public static void main(String args[]){
        CircularlyLinkedList<String> object = new CircularlyLinkedList<>();
        object.addFirst("Ankara");
        object.addFirst("Havana");
        object.addLast("Canberra");
        System.out.println("First city of the list: " + object.tail.getNext().getElement() + "\n" + "Size of the list: " + object.getSize());
        object.removeFirst();
        System.out.println("First city of the list: " + object.tail.getNext().getElement() + "\n" +
                "Last city of the list: " + object.tail.getElement() + "\n" +
                "Size of the list: " + object.getSize());
        object.addFirst("Utrecht");
        object.addLast("Toronto");
        object.removeLast();
        System.out.println("First city of the list: " + object.tail.getNext().getElement() + "\n" +
                "Last city of the list: " + object.tail.getElement() + "\n" +
                "Size of the list: " + object.getSize());
    }
}
