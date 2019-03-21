
public class ArrayList<E>  {

    private E[] data;
    private int size = 0;

    //constructors
    public ArrayList(int capacity){
        data = (E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    // checks if a given index is within the range
    public void checkIndex(int i, int n) throws IndexOutOfBoundsException {

        if (i < 0 || i > n){
            throw new IndexOutOfBoundsException("Illegal index");
        }

    }

    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }
    // replaces element e with the element in the index i and returns the previous element
    public E set(int i, E e) throws IndexOutOfBoundsException {

    checkIndex(i, size);
    E temp = data[i];
    data[i] = e;
    return temp;

    }
    // Inserts element e to be at index i, shifting all elements later. // O(n) time complexity
    public void add(int i, E e) throws IndexOutOfBoundsException {

        checkIndex(i, size + 1);
        if(size == data.length){
            throw new IllegalAccessError("Array is full");
        }
        for (int k = size - 1; k >= i; k--){
            data[k+1] = data[k];
        }
        data[i] = e;
        size++;
    }
    // O(n) time complexity
    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];

        for (int k = i; k < size - 1; k++){
            data[k] = data[k+1];
        }
        data[size - 1] = null;
        size--;
        return temp;

    }

    public static void main(String args[]){
        ArrayList<String> arrayList = new ArrayList<>(10);
        arrayList.add(3, "Hola amigos");
        arrayList.add(0, "Como estas?");
        arrayList.add(1, "Hasta luego");
        arrayList.add(2, "Yo soy una jugador");

        System.out.println( ":\n " + "Your 1st spanish sentence in the list:\n"  + arrayList.get(1));

    }

}

