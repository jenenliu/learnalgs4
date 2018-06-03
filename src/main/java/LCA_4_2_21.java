import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class LCA_4_2_21 {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] heights;

    public LCA_4_2_21(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        heights = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                heights[w] = heights[v] + 1;
                dfs(G, w);
            }
        }
    }

    public int lca(int v, int w) {
        while (heights[v] > heights[w]) {
            v = edgeTo[v];
        }
        while (heights[w] > heights[v]) {
            w = edgeTo[w];
        }
        if (v == w)
            return v;
        while (heights[v] >= 0) {
            v = edgeTo[v];
            w = edgeTo[w];
            if (v == w)
                return v;
        }

        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        LCA_4_2_21 lca = new LCA_4_2_21(new Digraph(in));

        // read lca.txt, test result
        StdOut.println("8 and 11's lca should be 2, is " + lca.lca(8, 11));
        StdOut.println("11 and 12's lca should be 9, is " + lca.lca(9, 11));
        StdOut.println("12 and 1's lca should be 0, is " + lca.lca(12, 1));
        StdOut.println("7 and 6's lca should be 2, is " + lca.lca(7,6));
    }
}
