import java.util.ArrayList;
import java.util.Comparator;


// bottom up heap construction with n entries takes 0n.
public class HeapBasedPQ<K, V> extends AbstractPriorityQueue<K, V> {
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapBasedPQ(){
        super();
    }

    public HeapBasedPQ(Comparator<K> comp){
        super(comp);
    }

    public HeapBasedPQ(K[] keys, V[] values){
        super();
        for(int j = 0; j < Math.min(keys.length, values.length); j++)
        {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    protected int parent(int j){
        return (j-1)/2;
    }
    protected int left(int j){
        return (2*j)+1;
    }
    protected int right(int j){
        return (2*j)+2;
    }
    protected boolean hasLeft(int j){
        return left(j) < heap.size();
    }
    protected boolean hasRight(int j){
        return right(j) < heap.size();
    }

    protected void swap(int i, int j){
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    protected void upheap(int j){
        while (j > 0){
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) > 0){
                break;
            }
            swap(j, p);
            j = p; // continues to the paren't location
        }
    }
    protected void downheap(int j){
        while (hasLeft(j)){
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)){
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0){
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0){
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }
    protected void heapify(){
        int startIndex = parent(size()-1);
        for (int j = startIndex; j >= 0; j--){
            downheap(j);
        }
    }



    public int size(){
        return heap.size();
    }

    public Entry<K,V> min(){
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K, V> insert(K key, V value){
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size()-1);
        return newest;
    }

    public Entry<K, V> removeMin(){
        if (heap.isEmpty()) return null;
        Entry<K, V> toBeRemoved = heap.get(0);
        swap(0, heap.size()-1);
        downheap(heap.size()-1);
        return toBeRemoved;
    }
}
