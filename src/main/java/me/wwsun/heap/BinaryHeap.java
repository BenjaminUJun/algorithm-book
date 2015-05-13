package me.wwsun.heap;

import java.nio.BufferUnderflowException;

/**
 * @param <E> is the type of elements contained in the binary heap
 */
public class BinaryHeap<E extends Comparable<? super E>> {

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Construct the binary heap
     * @param capacity the capacity of the binary heap
     */
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (E[]) new Comparable[capacity+1];
    }

    /**
     * Construct the binary heap given an array of items
     * @param items the given array to build the heap
     */
    public BinaryHeap(E[] items) {
        currentSize = items.length;
        array = (E[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (E item : items) {
            array[i++] = item;
        }

        buildHeap();
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed
     *
     * @param x the item to insert
     */
    public void insert(E x) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // percolate up
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    /**
     * Find the smallest item in the priority queue
     * @return the smallest item
     */
    public E findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return array[1];
    }

    /**
     * Remove the smallest item from the priority queue
     *
     * @return the smallest item, or throw UnderflowException, if empty
     */
    public E deleteMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        E minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize==0;
    }

    /**
     * Make the priority queue logically empty
     */
    public void makeEmpty() {
        currentSize =0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize; // number of elemetns in heap
    private E[] array; // the heap array

    /**
     * Internal method to percolate down in the heap.
     *
     * @param hole the index at which the percolate begins
     */
    private void percolateDown(int hole) {
        int child;
        E tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    /**
     * Establish heap order property from an arbitrary arrangement of items. Runs in linear time.
     */
    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {
        E [] old = array;
        array = (E[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }
}
