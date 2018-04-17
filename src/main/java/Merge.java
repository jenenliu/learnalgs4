import edu.princeton.cs.algs4.StdOut;


public class Merge {
    private static Comparable[] aux;      // auxiliary array for merge

    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];   // Allocate space just once.
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        // Sort a[lo...hi]
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, 0, mid);          // Sort left half.
        sort(a, mid+1, hi);       // Sort right half.
        merge(a, lo, mid, hi);       // Merge results
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)  a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j]))  a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) <= 0;
    }

    public static void main(String[] args)
    {
        Comparable[] a = {1,9,4,6,7,3,2,0};
        sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + ", ");
        StdOut.println();
    }
}
