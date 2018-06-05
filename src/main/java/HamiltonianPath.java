import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// exercise 4.2.24
public class HamiltonianPath {
    private boolean[] marked;
    Stack<Integer> postReverse;
    private int[] edgeTo;
    boolean isHamiltonianpath;

    public HamiltonianPath(Digraph G) {
        marked = new boolean[G.V()];
        postReverse = new Stack<Integer>();
        edgeTo = new int[G.V()];
        isHamiltonianpath = true;

        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, v);

        int node = postReverse.pop();
        while (!postReverse.isEmpty()) {
            int nextnode = postReverse.pop();
            boolean connected = false;
            for (int w : G.adj(node)) {
                if (w == nextnode)
                    connected = true;
            }
            if (!connected) {
                isHamiltonianpath = false;
                break;
            }
            node = nextnode;
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
        postReverse.push(v);
    }

    public Iterable<Integer> postreverse() {
        return postReverse;
    }

    public boolean isHamiltonPath() {
        return isHamiltonianpath;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        HamiltonianPath hamiltonianPath = new HamiltonianPath(new Digraph(in));
        StdOut.println(hamiltonianPath.isHamiltonPath());
    }
}
