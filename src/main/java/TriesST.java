import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class TriesST<Value> {
    private static int R = 256;  // radix
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value interactiveGet(String key) {
        if (root == null)
            return null;

        Node x = root;
        int d = key.length();
        for (int i = 0; i < d; i++) {
            char c = key.charAt(i);
            x = x.next[c];
            if (x == null)
                return null;
        }
        return (Value)x.val;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public Node interacitvePut(String key, Value val) {
        if (root == null)
            root = new Node();
        int d = key.length();
        Node x = root;

        for (int i = 0; i < d-1; i++) {
            char c = key.charAt(i);
            if (x.next[c] == null)
                x.next[c] = new Node();
            x = x.next[c];
        }
        if (x == null) {
            x = new Node();
        }
        x.val = val;
        return root;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        // Change value associated with key if in subtrie rooted at x.
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;

        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt;
    }

    public int interactiveSize() {
        if (root == null) return 0;

        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);
        int cnt = 0;

        while (!q.isEmpty()) {
            Node x = q.dequeue();
            if (x.val != null)
                cnt++;
            for (char c = 0; c < R; c++) {
                if (x.next[c] != null)
                    q.enqueue(x.next[c]);
            }
        }

        return cnt;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    // 获取字符串最后一个字符的Node
    private Node lastCharNode(String s) {
        Node x = root;
        if (x == null)
            return null;
        int d = s.length();
        for (int i = 0; i < d; i++) {
            char c = s.charAt(i);
            x = x.next[c];
            if (x == null)
                return null;
        }
        return x;
    }

    public Iterable<String> interactiveKeysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        Node x = lastCharNode(pre);
        interactiveCollect(x, pre, q);
        return q;
    }

    private class MyNode {
        Node node;
        String s;
        public MyNode(Node x, String str) {
            node = x;
            s = str;
        }
    }
    public void interactiveCollect(Node x, String pre, Queue<String> q) {
        if (x == null)
            return;

        Queue<MyNode> nodeq = new Queue<MyNode>();
        nodeq.enqueue(new MyNode(x, pre));
        while (!nodeq.isEmpty()) {
            MyNode myNode = nodeq.dequeue();
            if (myNode.node.val != null)
                q.enqueue(myNode.s);
            for (char c = 0; c < R; c++) {
                if (myNode.node.next[c] != null)
                    nodeq.enqueue(new MyNode(myNode.node.next[c], myNode.s+c));
            }
        }
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++)
            if (next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length())
            x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        if (x.val != null) return x;

        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    private static void printStrs(Iterable<String> strs) {
        for (String s : strs) {
            StdOut.println(s);
        }
    }

    public static void main(String[] args) {
        TriesST triesST = new TriesST();
        String[] allstr = "by see sells she shells shore the".split(" ");
        StdOut.println("--------------put strs----------------------");
        for (int i = 0; i < allstr.length; i++)
            triesST.put(allstr[i], i);
        StdOut.println("-------------print triesSt.keys()-----------");
        printStrs(triesST.keys());
        StdOut.println("-------------print triesSt.iteracvtiveKeys()---");
        printStrs(triesST.interactiveKeysWithPrefix(""));
        StdOut.println("-------------print size---------------------");
        StdOut.println(triesST.size());
        StdOut.println(triesST.interactiveSize());
        StdOut.println("-------------get test------------------------");
        StdOut.println("Using recursive get, 'by' = " + triesST.get("by") + ", 'shore' = " + triesST.get("shore") +
                ", 'null' = " + triesST.get("null"));
        StdOut.println("Using INTERACTIVE get, 'by' = " + triesST.interactiveGet("by") + ", 'shore' = " +
        triesST.interactiveGet("shore") + ", 'null' = " + triesST.interactiveGet("null"));
        StdOut.println("-------------keys with prefix 'sh'-----------------");
        printStrs(triesST.keysWithPrefix("sh"));
        StdOut.println("------------interactive keys with prefix 'sh'-------");
        printStrs(triesST.interactiveKeysWithPrefix("sh"));

        StdOut.println("-------------keys with prefix 's'-----------------");
        printStrs(triesST.keysWithPrefix("s"));
        StdOut.println("------------interactive keys with prefix 's'-------");
        printStrs(triesST.interactiveKeysWithPrefix("s"));
    }
}
