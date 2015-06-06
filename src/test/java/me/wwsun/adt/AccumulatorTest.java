package me.wwsun.adt;

import junit.framework.TestCase;
import stdlib.StdRandom;

public class AccumulatorTest extends TestCase {

    public void testAccumulator() {
        int T = 100000;
        Accumulator a = new Accumulator();
        for (int i = 0; i < T; i++) {
            a.addDataValue(StdRandom.uniform());
        }
        System.out.println(a);
    }

    public void testVisualAccumulator() {
        int T = 1000;
        VisualAccumulator a = new VisualAccumulator(T, 1.0);
        for (int i = 0; i < T; i++) {
            a.addDataValue(StdRandom.uniform());
        }

        System.out.println(a);
    }
}
