import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private Node pred, succ;
        private int N;
        private int height;

        public Node(Key key, Value val, int N, int h) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.height = h;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Node prex, Key key, Value val)
    {
        if (x == null)
        {
            x = new Node(key, val, 1, 1);
            if (prex == null)
                return x;
            if (prex.key.compareTo(key) > 0) {
                x.pred = prex.pred;
                if (x.pred != null)
                    x.pred.succ = x;
                prex.pred = x;
                x.succ = prex;
            } else {
                x.succ = prex.succ;
                if (x.succ != null)
                    x.succ.pred = x;
                prex.succ = x;
                x.pred = prex;
            }
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, x, key, val);
        else if (cmp > 0) x.right = put(x.right, x, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        x.height = 1 + Math.max(height_store(x.left), height_store(x.right));
        return x;
    }

    // 测试函数，为了测试pre和succ
    private void printTree()
    {
        Node p = root;
        // 找到最小节点
        while (p.left != null)
            p = p.left;
        // 然后通过succ来遍历
        for (; p != null; p = p.succ)
            StdOut.print(p.key + " ");
        StdOut.println();
    }

    private void reversePrintTree()
    {
        Node p = root;
        while (p.right != null)
            p = p.right;
        for (; p != null; p = p.pred)
            StdOut.print(p.key + " ");
        StdOut.println();
    }

    private Node put(Node x, Key key, Value val) {
        return put(x, x, key, val);
    }

    public int height_store() {
        return height_store(root);
    }

    private int height_store(Node x) {
        if (x == null) return 0;
        return 1 + Math.max(height_store(x.left), height_store(x.right));
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        return deleteMin(x, null);
    }

    private Node deleteMin(Node x, Node parent) {
        if (x.left == null) {
            if (parent != null) {
                parent.pred = x.right;
           }
//           if (x.right != null) {
//               x.right.pred = null;
//               if (x.right.left == null && x.right.right == null)
//                    x.right.succ = parent;
//            }
            if (x.right != null) {
                x.succ.pred = null;
            }

            return x.right;
        }
        x.left = deleteMin(x.left, x);
        x.N = size(x.left) + size(x.right) + 1;
        x.height = 1 + Math.max(height_store(x.left), height_store(x.right));
        return x;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return 0;
        if (x.left == null && x.right == null) return 1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.height = 1 + Math.max(height_store(x.left), height_store(x.right));
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    private boolean isOrdered(Node x) {
        // 空节点时返回真，递归函数退出的条件
        if (x == null)
            return true;

        // 判断当前节点的key是否在左节点和右节点之间
        boolean ordered =
                ((x.left == null && x.right == null) ||
                (x.left == null && x.right.key.compareTo(x.key) >= 0) ||
                (x.right == null && x.left.key.compareTo(x.key) <= 0) ||
                (x.left.key.compareTo(x.key) <= 0 && x.right.key.compareTo(x.key) >= 0));

        // 如果当前节点大于左节点小于右节点，并且左子树和右子树都是排好序的, 则整个树都是排好序的
        return ordered && isOrdered(x.left) && isOrdered(x.right);
    }

    public boolean isOrdered() {
        return isOrdered(root);
    }

    // 测试函数，用于在二叉树的最大节点后面加节点，可以加些小的节点来构造不合格的二叉树
    private void addToLast(Key key, Value val)
    {
        Node p = root;
        while (p.right != null)
        {
            p = p.right;
        }
        p.right = new Node(key, val, 1, 1);
    }

    // 测试函数，同上，只不过在最小节点的左子节点加
    private void addToFirst(Key key, Value val)
    {
        Node p = root;
        while (p.left != null)
            p = p.left;
        p.left = new Node(key, val, 1, 1);
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.put("k", 24);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("c", 30);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("d", 3);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("b", 1);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("r", 1);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("p", 3);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
        bst.put("o", 1);
        bst.deleteMin();
        StdOut.println("第一次删除最小值");
        bst.printTree();
        bst.reversePrintTree();
        bst.deleteMin();
        StdOut.println("第二次删除最小值");
        bst.printTree();
        bst.reversePrintTree();
        bst.deleteMin() ;
        StdOut.println("第三次删除最小值");
        bst.printTree();
        bst.reversePrintTree();
        bst.deleteMin();
        StdOut.println("第四次删除最小值");
        bst.printTree();
        bst.reversePrintTree();
        bst.deleteMin();
        StdOut.println("第五次删除最小值");
        bst.printTree();
        bst.reversePrintTree();
//        bst.addToLast("z", 4);
//        bst.addToFirst("a", 3);
//        bst.addToLast("a", 1);
//        bst.addToFirst("z", 2);
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
//        bst.delete("c");
//        bst.delete("o");
//        StdOut.println(bst.get("p"));
//        StdOut.println(bst.height());
//        StdOut.println(bst.height_store());
//        StdOut.println(bst.height_store());
//        if (bst.isOrdered())
//            StdOut.println("it is order");
//        else
//            StdOut.println("it is not order");
    }
}
