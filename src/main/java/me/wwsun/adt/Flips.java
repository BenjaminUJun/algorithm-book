package me.wwsun.adt;

import stdlib.StdIn;
import stdlib.StdOut;
import stdlib.StdRandom;

/**
 * 用来模拟掷硬币的过程
 */
public class Flips {
    public static void main(String[] args) {
        StdOut.println("Please input a number to specify the times of flips:");
        int T = StdIn.readInt();
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int t=0; t<T; t++) {
            if (StdRandom.bernoulli(0.5))
                heads.increment();
            else
                tails.increment();
        }
        StdOut.println(heads);
        StdOut.println(tails);

        int d = heads.tally() - tails.tally();
        StdOut.println("delta: " + Math.abs(d));
    }
}
