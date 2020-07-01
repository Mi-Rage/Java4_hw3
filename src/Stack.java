import java.util.NoSuchElementException;

public class Stack {
    private int capacity;
    private int[] stack;
    private int head;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.head = -1;
    }

    public boolean isEmpty() {
        return this.head == -1;
    }

    public boolean isFull() {
        return this.head == this.capacity - 1;
    }

    public void push(int value) {
        if (isFull()) {
            capacity *= 2;
            int[] newStack = new int[capacity];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }
        stack[++head] = value;
    }

    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return stack[head--];
    }

    public int peek() {
        return stack[head];
    }
}
