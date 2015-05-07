package me.wwsun;

import junit.framework.TestCase;
import me.wwsun.list.MyLinkedList;

public class ListTest extends TestCase {

    public void testMyLinkedList() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
