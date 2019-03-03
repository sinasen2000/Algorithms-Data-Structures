
// Unlike SLL and CLL, DLLs allow inserting an element at an arbitrary position
// Also, deleting element at the end of the list is efficient unlike SLL and CLL, thanks to the header and trailer nodes (dummy nodes/sentinels)
public class DoublyLinkedList<E> {
    //nested Node class
    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            next = n;
            prev = p;
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

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    // instance variables of DLL
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    // constructor
    public DoublyLinkedList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E first(){
        if (isEmpty()){
            return null;
        }
        return header.getNext().getElement();
    }
    public E last(){
        if (isEmpty()){
            return null;
        }
        return trailer.getPrev().getElement();
    }
    public void addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> node = new Node<>(e, pred, succ);
        pred.setNext(node);
        succ.setPrev(node);
        size++;
    }
    public E remove(Node<E> node){
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        return node.getElement();
    }
    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst(){
        if (isEmpty()){
            return null;
        }
        return remove(header.getNext());
    }
    public E removeLast(){
        if (isEmpty()){
            return null;
        }
        return remove(trailer.getPrev());
    }
    public static void main(String args[]){
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();

        dll.addFirst("Jumbo");
        dll.addLast("Albert Heijn");
        dll.addFirst("Lidl");
        // examples of addBetween, removeFirst and Last will be implemented later on.
    }
}
