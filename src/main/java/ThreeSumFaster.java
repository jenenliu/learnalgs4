import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ThreeSumFaster {
    public static int count(int[] a)
    {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
        {
            int j = 0;
            int k = N-1;
            for (; a[j] <= -a[i] && j < k; j++)
            {
                for (; a[j] + a[k] >= -a[i] && j != k && j > i && k > i; k--)
                    if (a[j]+a[k] == -a[i])
                        cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
