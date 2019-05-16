import java.util.Random;
import java.util.ArrayList;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    protected int n = 0; // number of entries
    protected int capacity;
    private int prime;
    private long scale, shift;

    public AbstractHashMap(int cap, int p){

        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime-1);
        shift = rand.nextInt(prime);

        createTable();

    }
    public AbstractHashMap(int cap){
        this(cap, 109345121); // default prime
    }

    public AbstractHashMap(){
        this(17); // default capacity
    }


    public int size(){
        return n;
    }

    public V get(K key){
        return bucketGet(hashValue(key), key);
    }

    public V remove(K key){
        return bucketRemove(hashValue(key), key);
    }

    public V put(K key, V value){
        V answer = bucketPut(hashValue(key), key, value);
        if(n>capacity){ // keeps load factor <= 0.5
            resize(2*capacity -1); //  finds a nearby odd or prime
        }
        return answer;
    }

    private int hashValue(K key){
        return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }

    private void resize(int newCap){

        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> e: entrySet()){
            buffer.add(e);
        }
        capacity = newCap;
        createTable();
        n = 0;
        for (Entry<K, V> e : buffer){
            put(e.getKey(), e.getValue());
        }
    }

    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}
