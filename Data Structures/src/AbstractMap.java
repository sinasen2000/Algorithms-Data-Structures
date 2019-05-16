import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K,V> {
    public boolean isEmpty() {
        return size()==0;
    }


    // nested map entry class
    protected static class MapEntry<K, V> implements Entry<K, V>{
        private K k;
        private V v;

        public MapEntry(K key, V value){
            k = key;
            v = value;
        }

        @Override
        public K getKey() {
            return k;
        }
        public V getValue(){
            return v;
        }
        protected void setKey(K key){
            k = key;
        }
        protected V setValue(V value){
            V old = v;
            v = value;
            return old;
        }
    }


    // support for public keySet method
    private class KeyIterator implements Iterator<K>{
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }
        public K next(){
            return entries.next().getKey();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    private class KeyIterable implements Iterable<K>{
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet(){return new KeyIterable();}

    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }
        public V next(){
            return entries.next().getValue();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public class ValueIterable implements Iterable<V>{
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public Iterable<V> values(){
        return new ValueIterable();
    }

}