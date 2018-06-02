import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.PriorityQueue;

public class DynamicMedian {
    private static PriorityQueue<Integer> minpq = new PriorityQueue<Integer>(10);
    private static PriorityQueue<Integer> maxpq = new PriorityQueue<Integer>(10,
            Collections.<Integer>reverseOrder());

    private void insert(Integer n)
    {
        if (maxpq.size() <= minpq.size())
            maxpq.add(n);
        else
            minpq.add(n);
    }

    public Integer getMedian()
    {
        if (maxpq == null && minpq == null)
            return null;
        else if (maxpq == null)
            return minpq.peek();
        else if (minpq == null)
            return maxpq.peek();
        else if (maxpq.size() == minpq.size())
            return (maxpq.peek() + minpq.peek()) / 2;
        else
            return maxpq.peek();
    }
    public static void main(String[] args)
    {
        maxpq.add(1);
        maxpq.add(9);
        minpq.add(4);
        minpq.add(0);

        StdOut.println("maxpq size = " + maxpq.size() + ", maxpq item = " + maxpq.poll() +
        ", after poll = " + maxpq.size());
        StdOut.println("minpq size = " + minpq.size() + ", minpq item = " + minpq.poll() +
        ", after poll = " + minpq.size());
    }
}
