import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        Node (Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public int size(Node x)
    {
        if (x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);

        return x;
    }

    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);

        return x;
    }

    private void flipColors(Node h)
    {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    public void put(Key key, Value val)
    {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val)
    {
        if (x == null)
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)  x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))  flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args)
    {
        RedBlackBST rbt = new RedBlackBST();
        String[] all = {"Y", "L", "P", "M", "X", "H", "C", "R", "A", "E", "S"};
        for (String a : all)
            rbt.put(a, 1);
        StdOut.println();
    }
}
