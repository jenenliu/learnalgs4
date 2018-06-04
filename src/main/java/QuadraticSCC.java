import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Strong component that required quadratic time complexity
// solution for exercise 4.2.23
public class QuadraticSCC {
    private boolean[] marked;
    private int[] id;
    private int[] edgeTo;
    private boolean[] onStack;
    private int count;
    private Stack<Integer> stack;

    public QuadraticSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        count = 1;

        for (int v = 0;  v < G.V(); v++) {
            if (id[v] == 0)
                dfs(G, v, v);
        }
    }

    private void dfs(Digraph G, int v, int originv) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, originv);
            } else if (onStack[w] && w == originv) {
                stack = new Stack<Integer>();
                boolean hascyclenode = false;
                int cyclecount = 0;
                for (int x = v; x != w; x = edgeTo[x]) {
                    if (id[x] != 0) {
                        hascyclenode = true;
                        cyclecount = id[x];
                        break;
                    }
                    stack.push(x);
                }
                for (int x = v; x != w; x = edgeTo[x]) {
                    if (hascyclenode)
                        id[x] = cyclecount;
                    else
                        id[x] = count;
                }
                stack.push(w);
                if (hascyclenode)
                    id[w] = cyclecount;
                else
                    id[w] = count;
                stack.push(v);
                if (!hascyclenode)
                    count++;
            }
        }
        onStack[v] = false;
        marked[v] = false;
    }

    public void printSCC() {
        Bag<Integer>[] components = new Bag[count];
        for (int i = 0; i < count; i++)
            components[i] = new Bag<Integer>();

        for (int i = 0; i < id.length; i++) {
            components[id[i]].add(i);
        }

        StdOut.println(count-1+components[0].size() + " components");
        for (int i = 0; i < count; i++) {
            for (int c : components[i]) {
                if (i == 0)
                    StdOut.println(c);
                else
                    StdOut.print(c + " ");
            }
            if (i != 0)
                StdOut.println();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        QuadraticSCC quadraticSCC = new QuadraticSCC(new Digraph(in));

        quadraticSCC.printSCC();
    }
}
