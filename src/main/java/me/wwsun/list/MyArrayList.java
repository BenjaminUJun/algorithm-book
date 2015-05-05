package me.wwsun.list;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) return;

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int index) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();

        return theItems[index];
    }

    public T set(int index, T newVal) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    /**
     * add the element to the end of list
     *
     * @param x is the element you want to add
     * @return true if add successfully, otherwise return false
     */
    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    /**
     * add the element to the specific position
     *
     * @param index is the position you want to insert the element
     * @param x     is the element you want to insert
     */
    public void add(int index, T x) {
        if (theItems.length == size()) ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = x;
        theSize++;
    }

    public T remove(int index) {
        T removeItem = theItems[index];
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }


    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    // inner class
    private class ArrayListIterator implements java.util.Iterator<T> {

        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current); // reference the outer class
        }
    }
}
