// Adapter design is followed: Meaning we effectively modify an existing class so that its methods match those of a related but different class.
// One way is to create an instance of the pre-existing class as a private field and then implementing each method of the new class through pre-existing class's methods.
//

import java.util.Arrays;
import java.util.Stack;

public class SLLBasedStack<E> {
    private SinglyLinkedList<E> listStack  = new SinglyLinkedList<>();; // empty list
    public SLLBasedStack(){

    }
    public int size(){
        return listStack.getSize();
    }
    public boolean isEmpty(){
        return listStack.isEmpty();
    }
    public void push(E e){
        listStack.addFirst(e);
    }
    public E pop(){
        if (isEmpty()){
            return null;
        }
        return listStack.removeFirst();
    }
    public E top(){
        if (isEmpty()){
            return null;
        }
        return listStack.first();
    }


    // this is an extra method to show the usage of array based stack

    public void reverse(E[] a){
        ArrayBasedStack<E> buffer = new ArrayBasedStack<>(a.length);
        for(int i = 0; i < a.length; i++){
            buffer.push(a[i]);
        }
        for(int i = 0; i < a.length; i++){
            a[i] = buffer.pop();
        }
    }

    public static void main(String args[]){
        SLLBasedStack<String> stack = new SLLBasedStack();
        stack.push("Lisa");
        stack.push("Jean");
        stack.push("Emilie");
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.top());

        String[] a = {"Clina", "Sina", "Irmak", "Ibrahim"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("Reversing");
        stack.reverse(a);
        System.out.println("a = " + Arrays.toString(a));
    }

}
