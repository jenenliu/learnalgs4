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
        Transaction p1 = new Transaction("Chicago",new Date(2016-1900, 7, 8), 34.9);
        Transaction p2 = new Transaction("Chicago", new Date(2017-1900,9,8), 40);
        Transaction p3 = new Transaction("Phoneix", new Date(2016-1900,9,9), 50);
        Transaction p4 = new Transaction("Seattle", new Date(2018-1900,9,8), 20);
        Transaction[] a = {p1, p2, p3, p4};
        StdOut.println("original");
        printTransation(a);
        sort(a, new Transaction.WhoOrder());
        StdOut.println("\n" + "=================who order====================");
        printTransation(a);
        sort(a, new Transaction.HowMuchOrder());
        StdOut.println("\n" + "=================how much order====================");
        printTransation(a);
        sort(a, new Transaction.WhenOrder());
        StdOut.println("\n" + "=================when order====================");
        printTransation(a);
    }
}
