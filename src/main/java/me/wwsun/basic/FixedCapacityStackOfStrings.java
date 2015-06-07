package me.wwsun.basic;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * A fixed capacity stack which could only contain Strings
 */
public class FixedCapacityStackOfStrings {

    private String[] a; // stack entries
    private int N;  // current stack size

    /**
     * Constructor
     * @param cap default stack size
     */
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }


    /**
     * 测试用例，每当碰到 - 时就弹出栈顶元素
     */
    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        In in = new In("data/tobe.txt");
        while(!in.isEmpty()) {
            String item = in.readString();
            if(!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }

        System.out.println("(" + stack.size() + " left on stack)");
    }
}
