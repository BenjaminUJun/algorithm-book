package me.wwsun.list;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        public E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() { clear(); }

    /**
     * change the size of this collection to zero
     */
    private void clear() {
        beginMarker = new Node<E>(null, null, null);
        endMarker = new Node<E>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() { return theSize; }

    public boolean isEmpty() { return size() == 0; }

    /**
     * Add the element to the tail of list
     * @param element
     * @return
     */
    public boolean add(E element) {
        add(size(), element);
        return true;
    }

    public void add(int index, E element) {
        addBefore(getNode(index), element);
    }

    public E remove(int index) {
        return remove(getNode(index));
    }

    /**
     * Removes the object contained in Node p
     * @param p the Node containing the object
     * @return the item was removed from the collection
     */
    private E remove(Node<E> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Adds an item to this collection, at specified position p
     * Items at or after that position are slid one position higher
     * @param p Node to add before
     * @param element any object
     */
    private void addBefore(Node<E> p, E element) {
        Node<E> newNode = new Node<E>(element, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }
    
    

    /**
     * Gets the Node at position index, which must range from 0 to size()
     * @param index is the index of node being obtained.
     * @return internal node corresponding to index
     * @throws IndexOutOfBoundsException if index is not between 0 and size()
     */
    private Node<E> getNode(int index) {
        Node<E> p;
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i=0; i<index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i>index; i++) {
                p = p.prev;
            }
        }

        return p;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E> {

        private Node<E> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public E next() {

            if (modCount != expectedModCount) throw new java.util.ConcurrentModificationException();
            if (!hasNext()) throw new java.util.NoSuchElementException();

            E nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount) throw new java.util.ConcurrentModificationException();
            if (!hasNext()) throw new java.util.NoSuchElementException();

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }

    private int theSize;
    private int modCount; // the time of changes which is begin from construction
    private Node<E> beginMarker;  // extra head node
    private Node<E> endMarker;  // extra tail node
}
