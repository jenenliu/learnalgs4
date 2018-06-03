import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Directed Eulerian cycle
public class Euler_4_2_20 {
    private boolean[] marked;
    private boolean[] onStack;
    Stack<Integer> cycle;
    boolean isEqualDegree;
    private int[] edgeTo;

    Euler_4_2_20(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        isEqualDegree = true;
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        isEqualDegree = isEqualDegree && G.equalDegree(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v ;
                isEqualDegree = isEqualDegree && G.equalDegree(w);
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public Iterable<Integer> cycles() {
        return cycle;
    }

    public boolean isEulerianCycle() {
        return cycle != null && isEqualDegree;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        Euler_4_2_20 euler = new Euler_4_2_20(new Digraph(in));

        if (euler.isEulerianCycle()) {
            for (int c : euler.cycles())
                StdOut.print(c + " ");
            StdOut.println();
            StdOut.println("is eulerian cycle");
        } else {
            StdOut.println("is NOT eulerian cycle");
        }
    }
}
