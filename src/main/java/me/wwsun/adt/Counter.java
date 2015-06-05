package me.wwsun.adt;

import stdlib.StdOut;
import stdlib.StdRandom;

public class Counter implements Comparable<Counter> {

    private final String name;  // counter name
    private int count = 0;      //current value

    public Counter(String id) {
        name = id;
    }

    /**
     * Increment the counter by 1
     */
    public void increment() {
        count++;
    }

    /**
     * @return the current count
     */
    public int tally() {
        return count;
    }

    @Override
    public String toString() {
        return count + " " + name;
    }

    @Override
    public int compareTo(Counter that) {
        if (this.count < that.count) return -1;
        else if (this.count > that.count) return 1;
        else return 0;
    }

    /**
     * Reads two integers N and T; creates N counters;
     * increments T counters at random; and prints results.
     */
    public static void main(String[] args) {
        int N = Integer.parseInt("20");
        int T = Integer.parseInt("15");

        // create N counters
        Counter[] hits = new Counter[N];
        for (int i = 0; i < N; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment T counters at random
        for (int t = 0; t < T; t++) {
            hits[StdRandom.uniform(N)].increment();
        }

        // print results
        for (int i = 0; i < N; i++) {
            StdOut.println(hits[i]);
        }
    }
}
