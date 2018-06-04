import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Strong component that required quadratic time complexity
// solution for exercise 4.2.23
public class QuadraticSCC {
    private boolean[] marked;
    private int[] id;
    private boolean[] onStack;
    private int count;
    private Stack<Integer> stack;

    public QuadraticSCC(Digraph G, int v) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        onStack = new boolean[G.V()];

        dfs(G, v, v);
    }

    private void dfs(Digraph G, int v, int originv) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (stack != null)
                return;
            if (!marked[w]) {
                id[w] = v;
                dfs(G, w, originv);
            } else if (onStack[w] && w == originv) {
                stack = new Stack<Integer>();
                for (int x = v; x != w; x = id[x]) {
                    stack.push(x);
                }
                stack.push(w);
                stack.push(v);
                count++;
            }
        }
        onStack[v] = false;
    }

    public void printSCC() {
        Bag<Integer>[] components = new Bag[count];

        for (int i = 0; i < count; i++) {
            components[i].add(id[i]);
        }

        StdOut.println(count + "components");
        for (int i = 0; i < count; i++) {
            for (int c : components[i]) {
                StdOut.print(c + " ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        QuadraticSCC quadraticSCC = new QuadraticSCC(new Digraph(in), 5);

        for (int c : quadraticSCC.stack)
            StdOut.print(c + " ");
        StdOut.println();
    }
}
