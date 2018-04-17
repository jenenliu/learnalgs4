import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class ThreeWayPartSort {
    public void sort(Comparable[] a)
    {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private void sort(Comparable[] a, int lo, int hi)
    {
        StdOut.println("lo = " + lo + ", hi = " + hi);
        if (hi <= lo) return;
        partition(a, lo, hi);
//        sort(a, lo, p.lo);
//        sort(a, p.hi, hi);
    }

    private class Position {
        public int lo;
        public int hi;
    }

    private void partition(Comparable[] a, int lo, int hi)
    {
        // [lo...p-1] [p, q], [q+1...hi]
        int i = lo, j = hi + 1;
        int p = lo+1, q = hi;
        Comparable v = a[lo];
        while (true)
        {
            while (!less(v, a[++i]))
            {
                if (equal(a[i], v))
                {
                    exch(a, i, p);
                    ++p;
                }
                if (i == hi) break;
            }
            while (!bigger(v, a[--j]))
            {
                if (equal(a[j], v))
                {
                    exch(a, j, q);
                    --q;
                }
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }

        i = j + 1;
        for (int k = lo; k < p; k++)
            exch(a, k, j--);
        for (int k = hi; k > q; k--)
            exch(a, k, i++);
        StdOut.println("what");
    }

    private boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    private boolean bigger(Comparable a, Comparable b)
    {
        return a.compareTo(b) > 0;
    }

    private boolean equal(Comparable a, Comparable b)
    {
        return a.compareTo(b) == 0;
    }

    private void exch(Comparable[] a, int i, int j)
    {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void printComparables(Comparable[] a)
    {
        for (Comparable anA : a) StdOut.print(anA + ", ");
        StdOut.println();
    }

    public static void main(String[] args)
    {
        Comparable[] a = {-2,-2, -2,-9,-2, -2, 11, 2, 8, -1, 1,-8, 9, 10, -2, -1,  -2, 100};
        ThreeWayPartSort ts = new ThreeWayPartSort();
//        ts.sort(a);

       ts.partition(a, 0, a.length-1);
//
//       printComparables(a);

//       p = ts.partition(a, 0, p.lo);
//       StdOut.println("p.lo = " + p.lo + ", pl.hi = " + p.hi);
       ts.printComparables(a);
    }
}
