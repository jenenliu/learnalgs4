import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true)
        {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void printComparables(Comparable[] a)
    {
        for (Comparable anA : a) StdOut.print(anA + ", ");
        StdOut.println();
    }

    public static void main(String[] args)
    {
        Comparable[] a = {-1, -2,-9,-2, -2, 10, 2, 8, 4, 1, 9, 10, 80, 3, 100};
//        Comparable[] a = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
//        int j = partition(a, 0, a.length-1);

        sort(a);

        printComparables(a);
//        StdOut.println(j);
//        Comparable c = "E";
//        Comparable b = "E";
//        StdOut.println(c.compareTo(b) < 0);
    }
}
