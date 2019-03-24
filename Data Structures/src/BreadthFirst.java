// traverses all positions at depth d before traversing positions of d+1

import java.util.Queue;

public class BreadthFirst<E> extends LinkedBinaryTree<E> {



    public void breadthFirst(Position<E> p) {
    SLLBasedQueue<Position<E>> q = new SLLBasedQueue<>();
    q.enqueue(p);
    while (!q.isEmpty()){
        p = q.dequeue();
        // visit(p); // visit method of traversals
        for (Position<E> c : children(p)){
            q.enqueue(c);
        }
    }
    }
}
