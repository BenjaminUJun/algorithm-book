package me.wwsun.sort;

import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.Stopwatch;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        // todo: other sorting algorithms

        return timer.elapsedTime();
    }


    public static double timeRandomInput(String alg, int N, int T) {
        // use alg to sort T random arrays of length N
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";

        int N = 1000;   // N arrays
        int T = 100;    // repeating the experiment T times

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
