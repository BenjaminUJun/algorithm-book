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
        a[N++] = item;
    }

    public E pop() {
        return a[--N];
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
