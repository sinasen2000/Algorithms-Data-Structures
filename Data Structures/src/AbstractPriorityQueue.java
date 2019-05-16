import java.util.Comparator;

public class AbstractPriorityQueue<K, V> {


    // nested PQEntry class
    protected static class PQEntry<K, V> implements Entry<K,V>{
        private K k;
        private V v;
        private PQEntry(K key, V value){
            k = key;
            v = value;
        }
        public K getKey(){
            return k;
        }
        public V getValue(){
            return v;
        }
        public void setKey(K key){
            k = key;
        }
        public void setValue(V value){
            v = value;
        }
    }
    // end of nested pqentry class

    // comparator of keys
    private Comparator<K> comp;
    int size;

    //creates an empty pq with given comparator
    protected AbstractPriorityQueue(Comparator<K> c){
        comp = c;
    }
    protected AbstractPriorityQueue(){
        this(new Comparator<K>() {
            @Override
            public int compare(K o1, K o2) {
                return 0;
            }
        });
    }
    protected int compare(Entry<K, V> a, Entry<K, V> b){

        return comp.compare(a.getKey(), b.getKey());

    }

    protected boolean checkKey(K key){
        try{
            return (comp.compare(key, key) == 0);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("Incompatible key");
        }
    }
    public boolean isEmpty(){
        return size == 0;
    }
}
