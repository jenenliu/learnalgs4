import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.HashMap;

public class Frequency {
    private Words[] pq;
    private int N = 0;
    private HashMap<String, Integer> wordmap = new HashMap<String, Integer>();

    public Frequency(int maxN)
    {
        pq = new Words[maxN+1];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void insert(String s)
    {
        Integer k = wordmap.get(s);
        if (k == null)
        {
            Words w = new Words(s, 1);
            pq[++N] = w;
            wordmap.put(s, N);
        } else {
            Words w = pq[k];
            w.addOne();
            swim(w, new Words.WordsOrder());
        }
    }

    private boolean less(Comparator c, Object v, Object w)
    {
        return c.compare(v, w) < 0;
    }

    private void exch(Words[] a, int i, int j)
    {
        Words t = a[i];
        a[i] = a[j];
        a[j] = t;
        wordmap.put(a[j].getWord(), j);
        wordmap.put(a[i].getWord(), i);
    }

    private void swim(Words w, Comparator c)
    {
        Integer k = wordmap.get(w.getWord());
        while (k > 1 && less(c, pq[k/2], pq[k]))
        {
            exch(pq,k/2, k);
            k = k/2;
        }
    }

    private void sink(Words w, Comparator c)
    {
        if (w == null)
            return;
        Integer k = wordmap.get(w.getWord());
        while (2 * k <= N)
        {
            int j = 2 *k;
            if (j < N && less(c, pq[j], pq[j+1])) j++;
            if (!less(c, pq[k], pq[j])) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private Words delMax()
    {
        Words max = pq[1];
        exch(pq, 1, N--);
        pq[N+1] = null;
        sink(pq[1], new Words.WordsOrder());
        return max;
    }

    public static void main(String[] args)
    {
        Frequency frequency = new Frequency(100);
        String[] teststr = {"a", "a", "b", "b", "b", "c", "c", "f", "f","e", "g", "g", "g", "g", "g"};
        for (String aTeststr : teststr) {
            frequency.insert(aTeststr);
        }
        while (!frequency.isEmpty())
        {
            Words w = frequency.delMax();
            StdOut.println(w.getCount() + " <---> " + w.getWord());
        }
    }
}
