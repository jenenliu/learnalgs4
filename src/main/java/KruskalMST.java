import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
        for (Edge e : G.edges())
            pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V()-1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v,w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0;
        for (Edge e : edges())
            weight += e.weight();

        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(in);

        KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);

        for (Edge e : kruskalMST.edges())
            StdOut.println(e.toString());

        StdOut.println(kruskalMST.weight());
    }
}
