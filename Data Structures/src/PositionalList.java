// we forego the index based methods because the integer index cant be applicable in some situations.
// we use linked lists when implementing positional lists because of the O(1) time insertions and deletions at arbitrary places.
// So this will be DLL based.
public class PositionalList<E> {

    // nested Node class
    private static class Node<E> {
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

       // to be continued

    }
