import edu.princeton.cs.algs4.StdOut;

import java.sql.Date;
import java.util.Comparator;

public class InsertSort {
    public static void sort(Object[]a, Comparator c)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0 && less(c, a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    public static void selectsort(Object[]a, Comparator c)
    {
        int N = a.length;
        for (int i = 0; i < N - 1; i++)
        {
            int k = i;
            for (int j = k; j < N; j++)
                if (less(c, a[j], a[k]))
                    k = j;
            exch(a, i, k);
        }
    }

    private static boolean less(Comparator c, Object v, Object w)
    {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j)
    {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void printTransation(Transaction[] t)
    {
        for(int i = 0; i < t.length; i++)
        {
            StdOut.println(t[i].toString());
        }
    }
    public static void main(String[] args)
    {
        Transaction p1 = new Transaction("Chicago",new Date(2017-1900, 7, 8), 34.9, 0);
        Transaction p2 = new Transaction("Chicago", new Date(2016-1900,9,8), 40, 1);
        Transaction p3 = new Transaction("Phoneix", new Date(2016-1900,9,9), 50, 2);
        Transaction p4 = new Transaction("Seattle", new Date(2018-1900,9,8), 20, 3);
        Transaction p5 = new Transaction("Chicago", new Date(2020-1900,9,8), 40, 4);
        Transaction p6 = new Transaction("Chicago", new Date(2017-1900,4,4), 40, 5);
        Transaction p7 = new Transaction("Seattle", new Date(2021-1900,5,5), 20, 6);
        Transaction p8 = new Transaction("Phoneix", new Date(2005-1900,9,9), 50, 7);

        Transaction[] a = {p1, p2, p3,p6, p5, p4, p7, p8};
        StdOut.println("original");
        printTransation(a);
        sort(a, new Transaction.WhoOrder());
        StdOut.println("\n" + "=================who order====================");
        printTransation(a);
        selectsort(a, new Transaction.WhoOrderWithIndex());
        StdOut.println("\n" + "=================who order with index====================");
        printTransation(a);
        sort(a, new Transaction.HowMuchOrder());
        StdOut.println("\n" + "=================how much order====================");
        printTransation(a);
        sort(a, new Transaction.WhenOrder());
        StdOut.println("\n" + "=================when order====================");
        printTransation(a);
    }
}
