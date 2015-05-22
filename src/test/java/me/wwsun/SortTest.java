package me.wwsun;

import junit.framework.TestCase;
import me.wwsun.sort.Sort;

public class SortTest extends TestCase {

    public void testInsertionSort() {
        Integer[] arr = {5, 3, 2, 1, 4};
        Sort.insertionSort(arr);
        printArray(arr);
    }

    public void testHeapSort() {
        Integer[] arr = {5, 3, 2, 1, 4};
        Sort.heapsort(arr);
        printArray(arr);
    }

    public void testMergeSort() {
        Integer[] arr = {5, 3, 2, 1, 4};
        Sort.mergeSort(arr);
        printArray(arr);
    }

    public void printArray(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
