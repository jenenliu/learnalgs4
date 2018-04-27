import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node
    {
        Key key;
        Value val;
        Node left, right;
        int N;

        public Node(Key key, Value val, int N)
        {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public Value get(Key key)
    {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }

        return null;
    }

    public Key min()
    {
        Node x = root;
        while (x.left != null)
            x = x.left;
        return x.key;
    }

    public Key max()
    {
        Node x = root;
        while (x.right != null)
            x = x.right;
        return x.key;
    }
    public void put(Key key, Value val)
    {
        Node x = root;

        if (x == null)
        {
            root = new Node(key, val, 1);
            return;
        }

        for (; ;)
        {
            int cmp = key.compareTo(x.key);
            if (cmp == 0)  { x.val = val; return; }
            else if (cmp < 0)
            {
                if (x.left == null) { x.left = new Node(key, val, 1); return; }
                x = x.left;
            }
            else
            {
                if (x.right == null) { x.right = new Node(key, val, 1); return; }
                x = x.right;
            }
        }
    }

    public Key floor(Key key)
    {
        Node x = root;
        if (x == null) return null;

        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) { x = x.left;}
            else if (cmp > 0)
            {
                if (x.right == null || key.compareTo(x.right.key) < 0)
                    return x.key;
                else
                    x = x.right;
            }
            else return x.key;
        }
        return null;
    }


    public static void main(String[] args)
    {
        NonrecursiveBST<Integer, String> nst = new NonrecursiveBST<Integer, String>();
        nst.put(10, "a");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(30, "b");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(20, "c");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(8, "d");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(5, "e");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(1, "o");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
        nst.put(100, "z");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println("");
//        StdOut.println(nst.get(20));
//        StdOut.println(nst.get(5));
//        StdOut.println(nst.get(100));
//        StdOut.println(nst.get(0));
//        StdOut.println(nst.get(19));
        StdOut.println(nst.floor(19));
        StdOut.println(nst.floor(20));
        StdOut.println(nst.floor(7));
        StdOut.println(nst.floor(4));
    }
}
