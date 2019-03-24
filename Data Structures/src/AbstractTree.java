import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p){
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return numChildren(p) >= 0;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }


    // depth = number of ancestors other than the node itself
    public int depth(Position<E> p){
        if(isRoot(p)){
            return 0;
        }
        else{
            return 1 + depth(parent(p));
        }
    }


    // the highest node is the root, height of the leaves are 0, exact opposite of depth.
    private int heightBad(){

        int h = 0;

        for(Position<E> p : positions()){
            if (isExternal(p)){
                h = Math.max(h, depth(p));
            }
        }
        return h;
    }

    public int height(Position<E> p){
        int h = 0;
        for(Position<E> c : children(p)){
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext(){ return posIterator.hasNext(); }
        public E next(){ return posIterator.next().getElement();}
        public void remove(){ posIterator.remove();}
    }

    public Iterator<E> iterator(){ return new ElementIterator();}

    private void preOrderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p); // visit method
        for (Position<E> c : children(p)){
            preOrderSubtree(c, snapshot);
        }
    }
    // method for doing a traversal for the whole tree
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            preOrderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    private void postOrderSubtree(Position<E> p, List<Position<E>> snapshot){
        for (Position<E> c : children(p)){
            postOrderSubtree(c, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()){
            postOrderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<Position<E>> breadthfirst(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            SLLBasedQueue<Position<E>> fringe = new SLLBasedQueue<>();
            fringe.enqueue(root());
            while (!(fringe.isEmpty())) {
                Position<E> p = fringe.dequeue();
                snapshot.add(p);
                for (Position<E> c : children(p)) {
                    fringe.enqueue(c);
                }
            }

        }
        return snapshot;
    }


}
