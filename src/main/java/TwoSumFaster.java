import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class TwoSumFaster {
    public static int count(int[] a)
    {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        int i= 0;
        int j = N - 1;
        for (; a[i] <= 0 && i < j; i++)
        {
            for (; a[j] >= -a[i] && i != j; j--)
            {
                if (a[i] + a[j] == 0)
                {
//                   StdOut.printf("%d %d\n", i, j);
                    cnt++;
                }

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
