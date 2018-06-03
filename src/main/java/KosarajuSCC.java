import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            StdOut.print(s + " ");
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
        StdOut.println();

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph dg = new Digraph(in);
        KosarajuSCC kosarajuSCC = new KosarajuSCC(dg);
        StdOut.println(kosarajuSCC.count() + " components");
        for (int i = 0; i < kosarajuSCC.count(); i++) {
            for (int j = 0; j < dg.V(); j++) {
                if (i == kosarajuSCC.id(j)) {
                    StdOut.print(j + " ");
                }
            }
            StdOut.println();
        }
    }
}
