package me.wwsun.basic;

import junit.framework.TestCase;
import stdlib.StdIn;
import stdlib.StdOut;

public class BasicStructureTest extends TestCase {

    public void testBag() {
        Bag<Double> numbers = new Bag<>();

        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }

        int N = numbers.size();

        double sum = 0.0;
        for (Double x : numbers) {
            sum += x;
        }

        double mean = sum / N;

        sum = 0.0;
        for (Double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double std = Math.sqrt(sum / (N - 1));

        System.out.printf("Mean: %.2f\n", mean);
        System.out.printf("Std dev: %.2f\n", std);
    }

    //
    public void testQueue() {

    }
}
