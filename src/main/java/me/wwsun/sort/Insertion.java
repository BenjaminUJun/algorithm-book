package me.wwsun.sort;

import stdlib.In;
import stdlib.StdOut;

public class Insertion {

    // this class should not be instantiate
    private Insertion() { }

    /**
     * Rearranges the array in ascending order, using the natural order
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    /*************************************************
     *  Helper sorting functions
     ************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // is the array a[] sorted?
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    // print array to standard output
    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    // test
    public static void main(String[] args) {
        In in = new In("data/tiny.txt");
        String[] a = in.readAllStrings();
        System.out.println("Before sorted: ");
        show(a);

        sort(a);
        assert isSorted(a);

        System.out.println("After sorted: ");
        show(a);
    }
}
