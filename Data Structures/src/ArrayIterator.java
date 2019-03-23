// An iterator is a software design pattern that abstracts the process of scanning through a sequence of elements, one element at a time.
// Java also has an iterator interface (java.util.Iterator) which has remove (may not be supported at all cases) hasNext and next methods.
// Two styles for iterator implementation:
// 1: Snapshot iterator: Maintains its own private copy of the squence of elements. Therefore it doesnt get affected by the changes made to the actual sequence.  Downside of it is that it needs O(n) time for consctruction, copy and store. Ex: For-each loop
// 2: Lazy iteratorL Doesnt make a copy, instead peforms a piecewise traversal of the structure only when the next() method is called to request another element. It requires O(1) constuction time and space.
// We will implement lazy iterators. This one is for arrays.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class IteratorImplementation<E> {

    private E[] data;
    private int size = 0;

    /**
     * an inner class
     */
    private class ArrayIterator implements Iterator<E> {

        private int j = 0; // index of the next element to report
        private boolean removable = false;


        public boolean hasNext(){
            return j < size;
        }

        public E next(){
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true;
            return data[j++];
        }

        public void remove(){
            if(!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(j-1);
            j--;
            removable = false;
        }

    }
}


//solve some issues here, not too important