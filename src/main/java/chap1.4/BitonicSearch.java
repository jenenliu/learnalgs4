import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BitonicSearch {
    private static boolean isPeek(int[] a, int i)
    {
        int N  = a.length;
        if (i >= N || i < 0)
            return false;
        if (N == 1)
            return true;
        if ((i == 0 || a[i-1] < a[i]) && (i == N - 1 || a[i+1] < a[i]))
            return true;
        return false;
    }

    private static int findPeek(int[] a)
    {
        int N = a.length;
        int high = N - 1;
        int low = 0;
        int mid = (low + high) / 2;

        while (low < high)
        {
            if (isPeek(a, mid))
                break;
            if (mid != 0 && a[mid] < a[mid-1])
            {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = (high + low) / 2;
            StdOut.printf("low = %d, high = %d", low, high);
            StdOut.printf("i = %d, a[i] = %d\n", mid, a[mid]);
        }

        return mid;
    }

    private static int reversebinarysearch(int[] a, int from, int to, int key) {
        assert (from >= 0 && from < a.length);
        assert (to >= 0 && to < a.length && from < to);
        int lo = from;
        int hi = to - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key > a[mid]) hi = mid - 1;
            else if (key < a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    private static int bitonicSearch(int[] a, int n)
    {
        int N = a.length;
        int peekpos = findPeek(a);
        if (peekpos == N -1 || peekpos == 0)
            return BinarySearch.indexOf(a, n);

        int npos = Arrays.binarySearch(a, 0, peekpos+1, n);
        if (npos >= 0)
            return npos;
        return reversebinarysearch(a, peekpos, N, n);
    }

    public static void main(String[] args)
    {
        //int[] a = {-9, -7, -1, 0, 1, 3, 4, 5, 8, 1, -2, -5, -6};
        int[] a = {2,3, 4, -1, -2, -3};
        int n = -3;
        StdOut.printf("pos of %d is %d", n, bitonicSearch(a, n));
    }
}
