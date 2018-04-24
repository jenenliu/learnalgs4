import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size()
    {
        return N;
    }

    private boolean isEmpty()
    {
        return N == 0;
    }

    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else                                     return null;
    }

    public void put(Key key, Value val)
    {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {
            vals[i] = val;
            return;
        }

        for (int j = N; j > i; j--)
        {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public int rank(Key key)
    {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else  return mid;
        }
        return lo;
    }

    public Key min()
    {
        return keys[0];
    }

    public Key max()
    {
        return keys[N-1];
    }

    public Key select(int k)
    {
        return keys[k];
    }

    public Key ceiling(Key key)
    {
        int i = rank(key);
        return keys[i];
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
//        if (contains(hi))
//            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public static void main(String[] args)
    {
//        BinarySearchST<Date, String> st = new BinarySearchST
        BinarySearchST<Time, Event> st = new BinarySearchST<Time, Event>(9);
        Time t1 = new Time("09:00:00");
        Time t2 = new Time("09:00:03");
        Time t3 = new Time("09:10:11");
        Time t4 = new Time("09:00:5");
        Event e1 = new Event("Chicago");
        Event e2 = new Event("Phoenix");
        Event e3 = new Event("Houston");
        Event e4 = new Event("Seattle");

        st.put(t1, e2);
        st.put(t2, e1);
        st.put(t3, e3);
        st.put(t4, e4);

        StdOut.println(st.ceiling(new Time("09:00:04")) + ", " + st.get(st.ceiling(new Time("09:00:04"))));
        StdOut.println(st.max() + ", " + st.get(st.max()));
        StdOut.println(st.min() + ", " + st.get(st.min()));
    }
}
