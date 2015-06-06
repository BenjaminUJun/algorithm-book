package me.wwsun.adt;

import stdlib.StdRandom;

/**
 * Created by Weiwei on 2015/6/5.
 */
public class FlipsMax {

    public static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally()) return x;
        else return y;
    }

    public static void main(String[] args) {
        int T = Integer.parseInt("100000");

        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < T; i++) {
            if (StdRandom.bernoulli(0.5))
                heads.increment();
            else
                tails.increment();
        }

        if (heads.tally() == tails.tally())
            System.out.println("Tie");

        else
            System.out.println(max(heads, tails) + " win");
    }
}
