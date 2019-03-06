import java.util.Stack;

// Simple and efficent
// But relies on a fixed-capacity array which limits the extension of the stack
//
//
public class ArrayBasedStack<E>  {
    private static final int CAPACITY = 1000; // fixed size of the array
    private E[] stack;
    private int t = -1; // index of top element of the stack. -1 == 0 in array conditions
    public ArrayBasedStack(){
        stack = (E[]) new Object[CAPACITY]; // creates the stack with the default capacity
    }

    public ArrayBasedStack(int capacity) {
        stack = (E[]) new Object[capacity]; // creates the stack with the given capacity
    }
    public int size(){
        return t+1;
    }
    public boolean isEmpty(){
        return t == -1;
    }

    public E  push(E e) throws IllegalStateException{
    if(size() == stack.length){
        throw new IllegalStateException();
    }
    stack[++t] = e;
    return e;
    }

    public E pop(){
        if(isEmpty()){ return null;}
        E element = stack[t];
        stack[t] = null;
        t--;
        return element;
    }

    public E top(){
        if(isEmpty()){ return null;}
        return stack[t];
    }


public static void main(String args[]){
    ArrayBasedStack<String> stack1 = new ArrayBasedStack<>(10);
    stack1.push("Lisa");
    stack1.push("Elena");
    stack1.push("Katarina");

    System.out.println(stack1.top());
    System.out.println(stack1.pop());
    System.out.println(stack1.top());

}
}
