package me.wwsun.search;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        In in = new In("data/largeW.txt"); // or tinyW.txt
        int[] whitelist = in.readAllInts(); // target file

        // sort the array
        Arrays.sort(whitelist);

        In check = new In("data/largeT.txt"); // or use tinyT.txt

        // check if the items in the target file or not
        while (!check.isEmpty()) {
            // If current number is not in the whitelist, print it!
            int key = check.readInt();
            if (rank(key, whitelist) < 0)
                StdOut.println(key);
        }
    }
}
