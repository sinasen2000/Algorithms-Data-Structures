/**
 * While arrays have a fixed size, SLLs can extend as you insert new items. However access to elements are done via traversal, in contrast to direct a
 * direct access of arrays.
 */
public class SinglyLinkedList<E> {
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

    //instance variables of SLL
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

    // access methods

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    //update methods

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node tailNode = new Node<>(e, null);
        if (isEmpty()) {
            head = tailNode;
        } else {
            tail.setNext(tailNode);
        }
        tail = tailNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E firstNode = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) tail = null;
        return firstNode;
    }
    public E removeLast(){
        if (isEmpty()) return null;
        E lastNode = tail.getElement();
        Node node = head;
        while(node.next != tail) {
            node = node.getNext();
        }
        tail = node;
        size--;
        if (size == 0) head = null;
        return lastNode;
    }

    public static void main(String args[]){
      SinglyLinkedList object = new SinglyLinkedList<>();
      object.addFirst( "Ankara");
      object.addLast("Istanbul");
      System.out.println("First city of the list: " + object.head.getElement() + "\n" + "Size of the list: " + object.getSize());
      object.addFirst( "Toronto");
      object.addFirst( "Moscow");
      object.addFirst( "Indiana");
      object.addFirst( "Delft");
      System.out.println("First city of the list: " + object.head.getElement() + "\n" + "Size of the list: " + object.getSize());
      object.removeFirst();
      System.out.println("First city of the list: " + object.head.getElement() + "\n" +
              "Last city of the list: " + object.tail.getElement() + "\n" +
              "Size of the list: " + object.getSize());
      object.removeLast();
        System.out.println("First city of the list: " + object.head.getElement() + "\n" +
                "Last city of the list: " + object.tail.getElement() + "\n" +
                "Size of the list: " + object.getSize());
    }
}

