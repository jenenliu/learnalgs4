import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {
    private int[] id;    // access to component id (site indexed)
    private int count;   // number of components
    private int[] treelen;  // store length of unoin tree (site indexed)

    public UF(int N)
    {  // Initialize component id array.
        count = N;
        id = new int[N];
        treelen = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        for (int i = 0; i < N; i++)
            treelen[i] = 0;
    }

    public int count()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    private int find(int p)
    {
        return id[p];
    }

    // quick-find  algorithm
    public void union(int p, int q)
    {   // Put p and q into the same component.
        int pID = find(p);
        int qID = find(q);

        // Nothing to do if p and q are already in the same component.
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }

    // quick-union union algorithm
    private int quickfind(int p)
    {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void quickunion(int p, int q)
    {
        int pRoot = quickfind(p);
        int qRoot = quickfind(q);
        if (pRoot == qRoot)
            return;

        id[pRoot] = qRoot;

        count--;
    }

    public void quickweightunion(int p, int q)
    {
        int pRoot = quickfind(p);
        int qRoot = quickfind(q);
        if (pRoot == qRoot)
            return;

        if (treelen[pRoot] > treelen[qRoot])
        {
            id[qRoot] = pRoot;
            treelen[pRoot] += treelen[qRoot];
        } else {
            id[pRoot] = qRoot;
            treelen[qRoot] += treelen[pRoot];
        }
        count--;
    }

    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q))
                continue;
            uf.quickweightunion(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
