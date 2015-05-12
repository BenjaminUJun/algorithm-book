package me.wwsun.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * @param <E> the type of elements contained in this hash table
 */
public class SeparateChainingHashTable<E> {

    /**
     * Construct the hash table
     */
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table
     *
     * @param size approximate table size
     */
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<E>();
        }
    }

    /**
     * Insert into the hash table. If the item is already present, then do nothing
     * @param x the item to insert
     */
    public void insert(E x) {
        List<E> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length)
                rehash();
        }
    }

    /**
     * Remove from the hash table
     *
     * @param x the item to remove
     */
    public void remove(E x) {
        List<E> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * Find an item in the hash table
     *
     * @param x the item to search for
     * @return true if x is not found
     */
    public boolean contains(E x) {
        List<E> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    /**
     * Make the hash table logically empty
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }


    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<E>[] theLists;
    private int currentSize;

    private void rehash() {
        List<E>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * theLists.length)];

        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<E>();
        }

        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (E item : oldLists[i]) {
                insert(item);
            }
        }
    }

    /**
     * A hash routine for string objects
     *
     * @param x the element to hash
     * @return the hash value
     */
    private int myhash(E x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;

        return hashVal;
    }

    private static int nextPrime(int n) {

        if(n%2 == 0)
            n++;

        for (; !isPrime(n); n +=2)
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
