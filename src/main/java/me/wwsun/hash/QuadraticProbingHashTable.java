package me.wwsun.hash;

/**
 * @param <E> is the type of elements that contained in this hash table
 */
public class QuadraticProbingHashTable<E> {

    /**
     * Construct the hash table
     */
    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table
     *
     * @param size the approximate initial size
     */
    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Make the hash table logically empty
     */
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * Find an item in the hash table
     *
     * @param x the item to search for
     * @return the matching item
     */
    public boolean contains(E x) {

        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * Insert into the hash table. IF the item is already present, do nothing
     *
     * @param x the item to insert
     */
    public void insert(E x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) return;

        array[currentPos] = new HashEntry<E>(x, true);

        //rehash
        if (++currentSize > array.length / 2)
            rehash();
    }

    /**
     * Remove from the hash table
     *
     * @param x the item to remove
     */
    public void remove(E x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    // inner class
    private static class HashEntry<E> {
        public E element;   // the element
        public boolean isActive; // false if marked deleted

        public HashEntry(E e) {
            this(e, true);
        }

        public HashEntry(E e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<E>[] array; // the array of elements
    private int currentSize;    // the number of occupied cells

    /**
     * Internal method to allocate array
     *
     * @param arraySize the size of array
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    /**
     * Return true if currentPos exists and is active
     *
     * @param currentPos the result of a call to findPos
     * @return true if currentPos is active
     */
    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Method that performs quadratic probing resolution
     *
     * @param x the item to search for
     * @return the position where the search terminates
     */
    private int findPos(E x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        return currentPos;
    }

    private void rehash() {
        HashEntry<E> [] oldArray = array;

        // create a new double-sized empty table
        allocateArray(nextPrime(2*oldArray.length));
        currentSize = 0;

        // copy table over
        for (int i = 0; i < oldArray.length; i++) {
            if(oldArray[i]!=null && oldArray[i].isActive)
                insert(oldArray[i].element);
        }
    }

    private int myhash(E x) {
        return 0;
    }

    private static int nextPrime(int n) {

        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
