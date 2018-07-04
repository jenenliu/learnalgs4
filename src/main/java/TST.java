import edu.princeton.cs.algs4.StdOut;
import sun.net.sdp.SdpProvider;

public class TST<Value> {
    private Node root;     // root of trie

    private class Node {
        char c;                 // character
        Node left, mid, right;  // left, middle, right subtrie
        Value val;              // value associated with string
    }

    public Value interactiveGet(String key) {
        Node x = root;

        for (int i = 0; i < key.length() - 1; ) {
            if (x == null)
                return null;
            char c = key.charAt(i);
            if (c == x.c) {
                x = x.mid;
                i++;
            }
            else if (c < x.c)
                x = x.left;
            else
                x = x.right;
        }

        return (Value)x.val;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null)  return null;
        return (Value)x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length()-1)
            return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) { x = new Node(); x.c = c;}
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d+1);
        else x.val = val;
        return x;
    }

    public void interactivePut(String key, Value val) {
        if (root == null) {
            root = new Node();
            root.c = key.charAt(0);
        }
        Node x = root;
        for (int i = 1; i < key.length()-1; ) {
            char c = key.charAt(i);
            if (x.left != null && c < x.c) {
                x = x.left;
            } else if (x.right != null && c > x.c) {
                x = x.right;
            } else if (x.mid != null && c == x.c) {
                x = x.mid;
                i++;
            } else {
                x.mid = new Node();
                x.mid.c = c;
                x = x.mid;
                i++;
            }
        }

        if (x == null)
            x = new Node();
        x.c = key.charAt(key.length()-1);
        x.val = val;
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<Integer>();
//        tst.put("hello", 0);
//        tst.put("world", 1);
//        tst.put("hello", 2);
//        tst.put("sh", 3);
        tst.interactivePut("hello", 0);
        tst.interactivePut("world", 1);
        tst.interactivePut("hello", 2);
        tst.interactivePut("sh", 3);
        StdOut.println(tst.get("sh"));
        StdOut.println(tst.interactiveGet("sh"));
        StdOut.println(tst.get("world"));
        StdOut.println(tst.interactiveGet("world"));
        StdOut.println(tst.get("hello"));
        StdOut.println(tst.interactiveGet("hello"));
        StdOut.println(tst.get("what"));
        StdOut.println(tst.interactiveGet("what"));
    }
}
