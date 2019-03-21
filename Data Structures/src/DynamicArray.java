// resizable, dynamic type array. This is also the Java's built in arrayList. Only difference of this class from normal arrayList class that we created
// is the resize method and add method which will now have a resiazable version.


// Amortized analysis: Multiplication = O(n), Geometric = O(1), Arithmetic = O(n^2)

public class DynamicArray<E> {

    private E[] data;
    private int size = 0;

    //constructors
    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // checks if a given index is within the range
    public void checkIndex(int i, int n) throws IndexOutOfBoundsException {

        if (i < 0 || i > n) {
            throw new IndexOutOfBoundsException("Illegal index");
        }

    }

    // resizes the array capacity with the given capacity
    public void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++){
            temp[i] = data[i];
        }
        data = temp;
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
        if (size == data.length) {
             resize(2*data.length);
        }
        for (int k = size - 1; k >= i; k--) {
            data[k + 1] = data[k];
        }
        data[i] = e;
        size++;
    }

    // O(n) time complexity
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];

        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }
        data[size - 1] = null;
        size--;
        return temp;

    }
}
