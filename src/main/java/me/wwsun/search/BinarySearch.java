package me.wwsun.search;

import stdlib.In;
import stdlib.StdIn;

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
        In in = new In("data/largeT.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        System.out.println("Please input a number you want to search: ");

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int pos = rank(key, whitelist);
            if (pos == -1) {
                System.out.println("Oops!" + key + " is not found!");
            } else {
                System.out.println("Key " + key + " is found!");
            }
        }
    }
}
