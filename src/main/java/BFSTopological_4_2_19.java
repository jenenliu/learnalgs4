import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BFSTopological_4_2_19 {
    private boolean[] marked;
    private int[] id;
    private int[] edgeTo;
    private int count;

    public BFSTopological_4_2_19(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                bfs(G, v);
            }
        }
    }

    private void bfs(Digraph G, int v) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(v);
        id[v] = count;
        marked[v] = true;
        while (!queue.isEmpty()) {
            queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    id[w] = id[v] + 1;
                    if (count < id[w])
                        count = id[w];
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public int count() {
        return count + 1;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Digraph dg = new Digraph(in);
//        BFSTopological_4_2_19 bfsTopological = new BFSTopological_4_2_19(dg);
//
//        int count = bfsTopological.count();
//        Bag<Integer>[] bags = new Bag[count];
//
//        for (int i = 0; i < count; i++)
//            bags[i] = new Bag<Integer>();
//
//        for (int i = 0; i < dg.V(); i++)
//            bags[bfsTopological.id(i)].add(i);
//
//        StdOut.println(count + " components");
//        for (int i = 0; i < count; i++) {
//            for (int item : bags[i])
//                StdOut.print(item + " ");
//            StdOut.println();
//        }
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph symbolDigraph = new SymbolDigraph(filename, separator);

        BFSTopological_4_2_19 bfsTopological = new BFSTopological_4_2_19(symbolDigraph.G());
         int count = bfsTopological.count();
        Bag<Integer>[] bags = new Bag[count];

        for (int i = 0; i < count; i++)
            bags[i] = new Bag<Integer>();

        for (int i = 0; i < symbolDigraph.G().V(); i++)
            bags[bfsTopological.id(i)].add(i);

        for (int i = 0; i < count; i++) {
            for (int item : bags[i])
                StdOut.println(symbolDigraph.name(item));
        }
    }
}

// BFS 无法做 Topological sort，因为有环的时候无法处理。BFS无法先把整个图
// 里面优先级最低(或最高)的先获取出来
//       |--> 2 --> 4
//  1 -->|          |
//       |--> 3  <--|
// 此图中可以看出，优先级最高的是3，但是BFS会在1处理完以后马上处理2和3，然后再处理4.
// 但是 DFS reverse post 会先去寻找优先级最高或者最低的，比如1-->2的时候，会继续
// 递归到4然后到3，保证了每次处理的时候，都是全局优先级排前的先处理，从而得到正确的
// Topological sort
