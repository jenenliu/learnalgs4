import edu.princeton.cs.algs4.StdOut;

public class UnBalancedRB3_3_23<Key extends Comparable<Key>, Value> {
    private static final boolean BLACK = false;
    private static final boolean RED = true;
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

    public int size()
    {
        Node x = root;
        return size(x);
    }

    private int size(Node x)
    {
        if (x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    public int height()
    {
        Node x = root;
        return height(x);
    }

    private int height(Node x)
    {
        if (x == null) return 0;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public void put(Key key, Value val)
    {
        root = put(root, root, key, val);
    }

    private Node put(Node x, Node parent, Key key, Value val)
    {
        if (x == null) {
            if (parent == null) {
                x = new Node(key, val, 1, BLACK);
                return x;
            }
            if (parent.color == RED) {
                x = new Node(key, val, 1, BLACK);
            } else if (parent.left != null && parent.left.color == RED) {
                x = new Node(key, val, 1, BLACK);
            } else if (parent.right != null && parent.right.color == RED) {
                x = new Node(key, val, 1, BLACK);
            } else {
                x = new Node(key, val, 1, RED);
            }
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, x, key, val);
        else if (cmp > 0) x.right = put(x.right, x, key, val);
        else x.val = val;
        x.N = 1 + size(x.left)
                + size(x.right);
        return x;
    }

    public static void main(String[] args)
    {
        UnBalancedRB3_3_23 urb = new UnBalancedRB3_3_23();
        String astr = "YZLPMXHCRAES";
        String[] strarr = astr.split("");
        for (String a : strarr)
            urb.put(a, 1);
        StdOut.println(urb.height());
    }
}
