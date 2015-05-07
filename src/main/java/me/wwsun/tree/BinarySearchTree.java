package me.wwsun.tree;

import java.nio.BufferUnderflowException;

/**
 *
 * @param <E> is the type of element contained in this tree
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private static class BinaryNode<E> {
        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;

        BinaryNode(E element) {
            this(element, null, null);
        }

        BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<E> root; // root node

    public BinarySearchTree() { root = null; }

    public void makeEmpty() { root = null; }

    public boolean isEmpty() { return root == null; }
    
    public boolean contains(E x) {
        return contains(x, root);
    }

    public E findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public E findMax() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    public void remove(E x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**
     * Internal method to find an item in a subtree
     * @param x is item to search for
     * @param root the node that roots the subtree
     * @return node containing the matched item
     */
    private boolean contains(E x, BinaryNode<E> root) {
        if (root == null) return false;

        int compareResult = x.compareTo(root.element);

        if (compareResult < 0)
            return contains(x, root.left);
        else if (compareResult > 0)
            return contains(x, root.right);
        else return true; // Match
    }

    /**
     * Internal method to find the smallest item in a subtree
     * @param root the node that roots the subtree
     * @return node containing the smallest item
     */
    private BinaryNode<E> findMin(BinaryNode<E> root) {
        if (root == null) return null;
        else if(root.left == null) return root;
        return findMin(root.left);
    }

    /**
     * Internal method to find the largest item in a subtree
     * @param root the node that roots the subtree
     * @return node containing the largest item
     */
    private BinaryNode<E> findMax(BinaryNode<E> root) {
//        if (root == null) return null;
//        else if(root.right == null) return root;
//        return findMax(root.right)
        if(root != null) {
            while (root.right != null)
                root = root.right;
        }
        return root;
    }

    /**
     * Internal method to insert into a subtree
     * @param x the item to insert
     * @param root the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<E> insert(E x, BinaryNode<E> root) {
        if(root == null) return new BinaryNode<E>(x, null, null); // create a new tree with one node

        int compareResult = x.compareTo(root.element);

        if(compareResult < 0)
            root.left = insert(x, root.left); // insert to the left subtree
        else if(compareResult > 0)
            root.right = insert(x, root.right); // insert to the right subtree
        else
            ; // Find duplicate node, do nothing
        return root;
    }

    /**
     * Internal method to remove a subtree
     * @param x the item to remove
     * @param root the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<E> remove(E x, BinaryNode<E> root) {
        if(root == null) return root; // Item not found, do nothing

        int compareResult = x.compareTo(root.element);

        if(compareResult < 0)
            root.left = remove(x, root.left);
        else if(compareResult > 0)
            root.right = remove(x, root.right);
        else if(root.left != null && root.right != null) { // two children
            root.element = findMin(root.right).element;
            root.right = remove(root.element, root.right);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }
}
