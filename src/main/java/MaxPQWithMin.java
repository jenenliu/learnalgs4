import edu.princeton.cs.algs4.StdOut;

public class MaxPQWithMin<Key extends Comparable<Key>> {
    private Key[] pq;  // heap-ordered complete binary tree
    private int N = 0;   // in qp[1....N] with pq[0] unused
    private Key minimal;

    public MaxPQWithMin(int maxN)
    {
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public Comparable min()
    {
        return (Comparable) minimal;
    }

    public void insert(Key v)
    {
        if (minimal == null || v.compareTo(minimal) < 0)
        {
            minimal = v;
        }
        pq[++N] = v;
        swim(N);
    }

    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j)
    {
        Key t = pq[j]; pq[j] = pq[i]; pq[i] = t;
    }

    public Key delMax()
    {
        Key max = pq[1];   // Retrieve max key from top.
        exch(1, N--);      // Exchange the last item.
        pq[N+1] = null;    // Avoid loitering.
        sink(1);          // Restore heap.
        return max;
    }

    public static void main(String[] args)
    {
        int N = 10;
        MaxPQWithMin<Integer> pq = new MaxPQWithMin<Integer>(N);
        for (int i = 0; i < N; i++)
        {
            pq.insert(i);
        }
        for (int i = 0; i < N; i++)
        {
            int max = pq.delMax();
            StdOut.println("max = " + max + ", min = " + pq.min());
        }
    }
}
