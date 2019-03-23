// every node has at most two children
// each child is being labeled as either left or right child
// a left child precedes a right child in the order of children of a node
// let n = number of nodes, ne, ni number of external and internal nodes, h = height of the tree. T has following properties:
// h + 1 <= n <

import java.util.ArrayList;
import java.util.List;

public class LinkedBinaryTree<E> extends AbstractTree<E>{

    // nested node class
    protected static class Node<E> implements Position<E>{

        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<E>(e, parent, left, right);
    }
    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree(){}

    protected Node<E> validate(Position<E> p){
        if(!(p instanceof Node)) throw new IllegalArgumentException("P is not a node");
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node){
            throw new IllegalArgumentException("P is no longer in the tree");
        }
        return node;
    }

    public int size(){
        return size;
    }

    public Position<E> root(){
        return root;
    }
    public Position<E> parent(Position<E> p){
        Node<E> node = validate(p);
        return node.getParent();
    }
    public Position<E> left(Position<E> p){
        Node<E> node = validate(p);
        return node.getLeft();
    }
    public Position<E> right(Position<E> p){
        Node<E> node = validate(p);
        return node.getRight();
    }

    // returns the sibling of a node at a position
    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent)) return right(parent);
        else return left(parent);
    }


    public int numChildren(Position<E> p){
        Node<E> node = validate(p);
        int count = 0;
        if(left(node) != null) count++;
        if(right(node) != null) count++;
        return count;
    }
    // returns an iterable collection of p's children's positions
    public Iterable<Position<E>> children(Position<E> p){
        List<Position<E>> snapshot = new ArrayList<>(2); // max capacity of 2
        if (left(p) != null) snapshot.add(left(p));
        if (right(p) != null) snapshot.add(right(p));
        return snapshot;
    }

    public Position<E> addRoot(E e) {
        if (!isEmpty()) throw new IllegalArgumentException("tree isn't empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    // adds a left child for the node at the position p
    public Position<E> addLeft(Position<E> p, E e){
        Node<E> parent = validate(p);
        if(!(parent.getLeft() == null)) throw new IllegalArgumentException("P already has a left child");
        Node<E> leftChild = createNode(e, parent, null, null);
        parent.setLeft(leftChild);
        size++;
        return leftChild;
    }

    public Position<E> addRight(Position<E> p, E e){
        Node<E> parent = validate(p);
        if(!(parent.getRight() == null)) throw new IllegalArgumentException("P already has a right child");
        Node<E> rightChild = createNode(e, parent, null, null);
        parent.setRight(rightChild);
        size++;
        return rightChild;
    }

    public E set(Position<E> p, E e){
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    // attaches trees t1 and t2 as left and right subtrees of external node p.
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2){
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("P already has a child");
        size += t1.size() + t2.size();
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }
    // removes the node at position p and replaces it with it child if there's any
    public E remove(Position<E> p){
        Node<E> node  = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("P has 2 children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null){
            child.setParent(node.getParent());
        }
        if (node == root){
            root = child;
        }
        else{
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()){
                parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null); // helping garbage collection
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node); // function for defunct node
        return temp;  // garbage collector
    }

}
