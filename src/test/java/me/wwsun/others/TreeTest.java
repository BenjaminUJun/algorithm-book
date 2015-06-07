package me.wwsun.others;

import junit.framework.TestCase;
import me.wwsun.tree.BinarySearchTree;

public class TreeTest extends TestCase {

    public void testBinarySearchTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        bst.insert(3);
        bst.insert(6);

        bst.printTree();
        System.out.println("Tree height:\t" + bst.height());

        assertEquals(bst.findMax(), new Integer(6));
        assertEquals(bst.findMin(), new Integer(3));
    }
}
