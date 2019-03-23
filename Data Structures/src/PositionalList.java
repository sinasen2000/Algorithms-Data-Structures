// we forego the index based methods because the integer index cant be applicable in some situations.
// we use linked lists when implementing positional lists because of the O(1) time insertions and deletions at arbitrary places.
// So this will be DLL based.
public class PositionalList<E> {

    // nested Node class
    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            if (next == null) {
                throw new IllegalArgumentException("position not valid");
            }
                return element;
            }

            public void setElement (E element){
                this.element = element;
            }

            public Node<E> getPrev () {
                return prev;
            }

            public void setPrev (Node < E > prev) {
                this.prev = prev;
            }

            public Node<E> getNext () {
                return next;
            }

            public void setNext (Node < E > next) {
                this.next = next;
            }
        }


        // instance variables of Positional List

        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        // constructs a new empty list
        public PositionalList() {
            header = new Node<>(null, null, null);
            trailer = new Node<>(null, header, null);
            header.setNext(trailer);
        }

       private Node<E> validate(Position<E> p){
            if (!(p instanceof Node)){
                throw new IllegalArgumentException("Invalid input");
            }
            Node<E> node = (Node<E>) p;
            if(node.getNext() == null){
                throw new IllegalArgumentException("p isnt in the list anymore");
            }
            return node;
       }

       private Position<E> position(Node<E> node){
            if(node == header || node == trailer){
                return null;
            }
            return node;
       }



       public int size(){
            return size;
       }
       public boolean isEmpty(){
            return size == 0;
       }
       public Position<E> first(){
            return position(header.getNext());
       }

       public Position<E> last(){
            return position(trailer.getPrev());
       }
       public Position<E> before(Position<E> p) throws IllegalArgumentException{
            Node<E> node = validate(p);
            return position(node.getPrev());
       }
       public Position<E> after(Position<E> p) throws IllegalArgumentException{
           Node<E> node = validate(p);
           return position(node.getNext());
       }




       private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
            Node<E> node = new Node<>(e, pred, succ);
            pred.setNext(node);
            succ.setPrev(node);
            size++;
            return node;
       }





       public Position<E> addFirst(E e){
            return addBetween(e, header, header.getNext());
       }
       public Position<E> addLast(E e){
            return addBetween(e, trailer.getPrev(), trailer);
       }

       public Position<E> addBefore(Position<E> p, E e){
            Node<E> node = validate(p);
            return addBetween(e, node.getPrev(), node);
       }
       public Position<E> addAfter(Position<E> p, E e){
            Node<E> node = validate(p);
            return addBetween(e, node, node.getNext());
       }


       public E set(Position<E> p, E e){
            Node<E> node = validate(p);
            E garbageElement = node.getElement();
            node.setElement(e);
            return garbageElement;
       }

       public E remove(Position<E> p){
            Node<E> node = validate(p);
            E garbageElement = node.getElement();
            Node<E> pred = node.getPrev();
            Node<E> succ = node.getNext();
            pred.setNext(succ);
            succ.setPrev(pred);
            size--;
            // help with garbage collection(not necessary but still good), without these, this method will basically be like add between.
            node.setElement(null);
            node.setPrev(null);
            node.setNext(null);
            return garbageElement;
       }


    }
