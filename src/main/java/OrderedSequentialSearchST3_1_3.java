import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class OrderedSequentialSearchST3_1_3<Key extends Comparable<Key>, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node n)
        {
            this.key = key;
            this.val = val;
            this.next = n;
        }
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        int count = 0;
        for (Node p = first; p != null; p = p.next)
            ++count;
        return count;
    }

    public Value get(Key key)
    {
        if (first == null) return null;
        for (Node p = first; p != null; p = p.next)
            if (p.key.compareTo(key) == 0)
                return p.val;
        return null;
    }

    public void put(Key key, Value val)
    {
        StdOut.println("key = " + key + ", val = " + val);
        if (first == null || first.key.compareTo(key) > 0)
        {
            first = new Node(key, val, first);
            return;
        }
        Node p = first;
        while (p.next != null && p.next.key.compareTo(key) <= 0)
            p = p.next;
        if (p.key.compareTo(key) == 0)
        {   // 如果找到了key，则更新对应的值
            p.val = val;
            return;
        }
        Node n = new Node(key, val, null);
        n.next = p.next;
        p.next = n;
    }

    public Value min()
    {
        return first.val;
    }

    public Value max()
    {
        Node p = first;
        while (p.next != null)
            p = p.next;
        return p.val;
    }

    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<Key>();
        for (Node p = first; p != null; p = p.next)
            q.enqueue(p.key);
        return q;
    }

    public static void main(String[] args)
    {
        OrderedSequentialSearchST3_1_3<String, Integer> st;
        st = new OrderedSequentialSearchST3_1_3<String, Integer>();
        String[] str = {"f","d", "a", "a", "b", "c", "d"};

        for (int i = 0; i < str.length; i++)
        {
            String key = str[i];
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
