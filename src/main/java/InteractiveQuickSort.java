import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class InteractiveQuickSort {
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

    private static void sort(Comparable[] a)
    {
        int lo = 0;
        int hi = a.length - 1;
        Stack<Integer> st = new Stack<Integer>();
        st.push(lo);
        st.push(hi);
        while (!st.isEmpty())
        {
            hi = st.pop();
            lo = st.pop();
            int p = partition(a, lo, hi);
            if (lo < p-1)
            {
                st.push(lo);
                st.push(p-1);
            }
            if (hi > p + 1)
            {
                st.push(p+1);
                st.push(hi);
            }
        }
    }

    private static void printComparables(Comparable[] a)
    {
        for (Comparable anA : a) StdOut.print(anA + ", ");
        StdOut.println();
    }

    public static void main(String[] args)
    {
        Comparable[] a = {4,3,3,3,4,4,3,4,55};
        sort(a);
        printComparables(a);
    }
}
