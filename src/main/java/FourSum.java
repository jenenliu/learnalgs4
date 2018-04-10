import edu.princeton.cs.algs4.StdOut;

public class FourSum {
    public static int count(int[] a)
    {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    for (int l = k+1; l < N; l++)
                        if (a[i]+a[j]+a[k]+a[l] == 0)
                            cnt++;
        return cnt;
    }

    public static void main(String[] args)
    {
        int[]a = {-1, 0, 3, -2, 5, 6, 7};
        StdOut.println(count(a));
    }
}
