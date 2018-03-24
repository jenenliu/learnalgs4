import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TowSum {
    public static int count(int[] a)
    {
        int N = a.length;
        int cnt = 0;
        for(int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (a[i] + a[j] == 0)
                    cnt++;
        return cnt;
    }

   public static void main(String[] args)
   {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
   }
}
