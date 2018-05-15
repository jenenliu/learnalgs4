import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

public class DoubleProbSeparateChainingHST<Key, Value> {
    private static final int INIT_CAPACITY = 3;

    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;

     public DoubleProbSeparateChainingHST() {
         this(INIT_CAPACITY);
     }

     public DoubleProbSeparateChainingHST(int cap) {
         this.m = cap;
         st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[cap];

         for (int i = 0; i < cap; i++)
             st[i] = new SequentialSearchST<Key, Value>();
     }

     private void resize(int chains) {
         DoubleProbSeparateChainingHST<Key, Value> temp =
                 new DoubleProbSeparateChainingHST<Key, Value>(chains);
         for (int i = 0; i < m; i++) {
             int j = 1;
             for (Key key : st[i].keys()) {
                 temp.put(j, key, st[i].get(key));
                 j++;
             }
         }

         this.m = temp.m;
         this.n = temp.n;
         this.st = temp.st;
     }

     public int size() {
         return n;
     }

     public void put(int i, Key key, Value val) {
         if (n >= 10*m) resize(2*m);

         int hash1 = hash1(i);
         int hash2 = hash2(i);
         if (st[hash1].contains(key)) {   // 检查是否已在其中一个链表中
             st[hash1].put(key, val);
             return;
         }
         if (st[hash2].contains(key)) {
             st[hash2].put(key, val);
             return;
         }

         if (st[hash1].size() < st[hash2].size()) {
             st[hash1].put(key, val);
         } else {
             st[hash2].put(key, val);
         }
         n++;
     }

     public Value get(int i, Key key) {
         int hash1 = hash1(i);
         int hash2 = hash2(i);
         if (st[hash1].contains(key))
             return st[hash1].get(key);
         return st[hash2].get(key);
     }

     public void delete(int i, Key key) {
         int hash1 = hash1(i);
         int hash2 = hash2(i);

         if (st[hash1].contains(key)) {
             n--;
             st[hash1].delete(key);
         } else if (st[hash2].contains(key)) {
             n--;
             st[hash2].delete(key);
         }

         if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
     }

     private int hash1(int index) {
         return (11 * index) % m;
     }

     private int hash2(int index) {
         return (11 * index) % m;
     }

     public Iterable<Key> keys() {
         Queue<Key> queue = new Queue<Key>();
         for (int i = 0; i < m; i++)
             for (Key key : st[i].keys())
                 queue.enqueue(key);
         return queue;
     }

     public static void main(String[] args) {
         DoubleProbSeparateChainingHST<String, Integer> st = new
                 DoubleProbSeparateChainingHST<String, Integer>();
         String[] astr = "EASYQUESTION".split("");
         int i = 1;
         for (String a : astr) {
             st.put(i, a, i);
             i++;
         }
         for (String k : st.keys()) {
             StdOut.print(k + " ");
         }
         StdOut.println();
     }
}
