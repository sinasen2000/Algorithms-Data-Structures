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
}
