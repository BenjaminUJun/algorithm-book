package me.wwsun.basic;

import stdlib.In;
import stdlib.StdOut;

/**
 * A fixed capacity stack
 *
 * @param <E> is the type of element contained in this stack
 */
public class FixedCapacityStack<E> {

    private E[] a; // stack entries
    private int N;  // current stack size

    /**
     * Constructor
     *
     * @param cap default stack size
     */
    public FixedCapacityStack(int cap) {
        a = (E[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(E item) {

        // if current stack is full, resize the stack
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public E pop() {
        E item = a[--N];
        a[N] = null; // 避免对象游离
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }


    /**
     * resize current stack, move current stack elements to a new stack
     *
     * @param newSize the size of new stack
     */
    private void resize(int newSize) {
        E[] temp = (E[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    /**
     * 测试用例，每当碰到 - 时就弹出栈顶元素
     */
    public static void main(String[] args) {
        FixedCapacityStack stack = new FixedCapacityStack(100);
        In in = new In("data/tobe.txt");
        while (!in.isEmpty()) {
            String item = in.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }

        System.out.println("(" + stack.size() + " left on stack)");
    }
}
