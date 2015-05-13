package me.wwsun;

import junit.framework.TestCase;
import me.wwsun.heap.BinaryHeap;

public class HeapTest extends TestCase {

    public void testBinaryHeap() {
        int numItems = 10000;
        int i;

        BinaryHeap<Integer> h = new BinaryHeap<Integer>();

        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.insert(i);

        for (i = 1; i < numItems; i++)
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
    }
}
