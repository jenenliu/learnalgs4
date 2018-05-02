import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }

        return null;
    }

    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        if (x == null)
            return null;
        while (x.left != null)
            x = x.left;
        return x.key;
    }

    //    public int size()
//    {
//        return size(root);
//    }
//
//    private int size(Node x)
//    {
//        if (x == null)
//            return 0;
//        return x.N;
//    }
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        if (x.right == null && x.left == null)
            return 1;
        return 1 + size(x.right) + size(x.left);
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (x == null)
            return null;
        while (x.right != null)
            x = x.right;
        return x.key;
    }

    public void put(Key key, Value val) {
        Node x = root;

        if (x == null) {
            root = new Node(key, val, 1);
            return;
        }

        for (; ; ) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.val = val;
                return;
            } else if (cmp < 0) {
                if (x.left == null) {
                    x.left = new Node(key, val, 1);
                    x.N = 1 + size(x.right) + size(x.left);
                    return;
                }
                x = x.left;
            } else {
                if (x.right == null) {
                    x.right = new Node(key, val, 1);
                    x.N = 1 + size(x.right) + size(x.left);
                    return;
                }
                x = x.right;
            }
        }
    }

    public Key floor(Key key) {
        Node x = root;
        if (x == null) return null;

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                Key minright = min(x.right);
                if (minright == null || key.compareTo(minright) < 0)
                    return x.key;
                else
                    x = x.right;
            } else return x.key;
        }
        return null;
    }


    public Key select(int ind)
    {
        Node x = root;
        if (x == null)
            return  null;

        if (ind < 0 || ind >= size(x))
            return null;

        int lsize = size(x.left);
        while (lsize != ind) {
            if (lsize < ind)
            {
                ind = ind - lsize - 1;
                if (x.right == null)
                    break;
                x = x.right;
                lsize = size(x.left);
            } else {
                x = x.left;
                lsize = size(x.left);
            }
        }

        return x.key;
    }

    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        Stack<Node> sk = new Stack<Node>();
        // 这里会找到大于等于min和小于等于max的最小节点
        while (x != null)
        {
            int cmplo = x.key.compareTo(lo);
            int cmphi = x.key.compareTo(hi);
            if (cmplo >= 0 && cmphi <= 0)   // 如果节点在min和max之间，则继续找下去
            {
                sk.push(x);
                x = x.left;
            } else if (cmplo < 0 && x.right != null)   // 如果节点小于min，则最小节点有可能在右边
                x = x.right;
            else if (cmphi > 0 && x.left != null)   // 如果节点大于max，则最小节点有可能在左边
                x = x.left;
            else
                x = x.left;   // 如果都不符合，则表示节点已经超出min-max的边界
        }

        while (!sk.isEmpty())
        {
            // 每次拿出栈里面的节点后，放入队列，然后看其右边，如果有就处理，没有就略过
            Node p = sk.pop();
            queue.enqueue(p.key);
            if (p.right != null)
            {
                p = p.right;
                while (p != null)
                {
                    int cmplo = p.key.compareTo(lo);
                    int cmphi = p.key.compareTo(hi);
                    if (cmplo >= 0 && cmphi <= 0)
                    {
                        sk.push(p);
                        p = p.left;
                    } else if (cmplo < 0 && p.right != null)
                        p = p.right;
                    else if (cmphi > 0 && p.left != null)
                        p = p.left;
                    else
                        p = p.left;
                }
            }
        }
    }


    public static void main(String[] args)
    {
        NonrecursiveBST<Integer, String> nst = new NonrecursiveBST<Integer, String>();
        nst.put(10, "a");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.select(1));
        nst.put(30, "b");
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.select(1));
//        StdOut.println(nst.select(2));
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
        nst.put(20, "c");
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
        nst.put(6, "d");
        nst.put(7, "d");
        nst.put(9, "k");
        nst.put(8, "a");
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.select(1));
//        StdOut.println(nst.select(2));
//        StdOut.println(nst.select(3));
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
        nst.put(5, "e");
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
        nst.put(1, "o");
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
        nst.put(100, "z");
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.min());
//        StdOut.println(nst.max());
//        StdOut.println(nst.size());
//        StdOut.println("");
        nst.put(110, "a");
        nst.put(105, "b");
        nst.put(106, "c");

        for (Integer k : nst.keys(5, 101))
        {
            StdOut.println(k);
        }
//        StdOut.println(nst.select(0));
//        StdOut.println(nst.select(1));
//        StdOut.println(nst.select(2));
//        StdOut.println(nst.select(3));
//        StdOut.println(nst.select(4));
//        StdOut.println(nst.select(5));
//        StdOut.println(nst.select(6));
//        StdOut.println(nst.select(7));
//        StdOut.println(nst.get(20));
//        StdOut.println(nst.get(5));
//        StdOut.println(nst.get(100));
//        StdOut.println(nst.get(0));
//        StdOut.println(nst.get(19));

//        StdOut.println(nst.floor(19));
//        StdOut.println(nst.floor(20));
//        StdOut.println(nst.floor(7));
//        StdOut.println(nst.floor(4));
//        StdOut.println(nst.floor(1));
//        StdOut.println(nst.floor(0));
//        StdOut.println(nst.floor(100));
//        StdOut.println(nst.floor(101));
//        StdOut.println(nst.floor(31));
//        StdOut.println(nst.floor(22));
    }
}