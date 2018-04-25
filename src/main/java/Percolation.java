import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private final int num;    // number of col or row
    private int[] cells;
    private final static int block = -1;
    private int count = 0;    // number of opening site

    public Percolation(int n)  // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new IllegalArgumentException();
        num = n;
        cells = new int[num * num];
        for (int i = 0; i < cells.length; i++)
            cells[i] = block;
    }

    private int getInd(int row, int col)
    {
        return (row-1) * num + (col - 1);
    }

    private boolean connected(int p, int q)
    {
//        StdOut.println("p = " + p + ", cell[p] = " + cells[p] + ", q = " + q + ", cells[q] = " + cells[q]);
        int pRoot = find(p);
        int qRoot = find(q);
        return pRoot == qRoot;
    }

    private void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (pRoot < qRoot)
            cells[qRoot] = pRoot;
        else
            cells[pRoot] = qRoot;
    }

    private int find(int p)
    {
        while (cells[p] != p)
            p = cells[p];
        return p;
    }

    public void open(int row, int col)   // open site(row, col) if it is not open already
    {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();

        int ind = getInd(row, col);
        if (cells[ind] == block)
        {
            count++;
            cells[ind] = ind;
        }
        if (row - 1 >= 1 && isOpen(row-1, col))
        {
            union(ind-num, ind);
        }
        if (row + 1 <= num && isOpen(row+1, col))
        {
            union(ind+num, ind);
        }
        if (col - 1 >= 1 && isOpen(row, col-1))
        {
            union(ind- 1, ind);
        }
        if (col + 1 <= num && isOpen(row, col+1))
        {
            union(ind+ 1, ind);
        }
    }

    public boolean isOpen(int row, int col)  // is site(row, col) open ?
    {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();
        int ind = getInd(row, col);
        return cells[ind] != block;
    }

    public boolean isFull(int row, int col)   // is site(row, col) full ?
    {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();
        if (!isOpen(row, col))
            return false;
        if (row == 1 )
            return true;
        int ind = getInd(row, col);
        int p = find(ind);
        if (p >= 0 && p <= getInd(1, num))
            return true;
        return false;
//        for (int i = 1; i <= num; i++)
//        {
//            if (isOpen(row, col) && isOpen(1, i) && connected(getInd(1, i), ind))
//                return true;
//        }
//        return false;
    }

    public int numberOfOpenSites()   // number of open sites
    {
        return count;
    }

    public boolean percolates()   // does the system percolate?
    {
        for (int i = 1; i <= num; i++)
        {
            if (!isOpen(num, i))
                continue;
            if (isFull(num, i))
                return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        int n = 8;
        Percolation percolation = new Percolation(n);
        percolation.open(1, 1);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("isfull:" + percolation.isFull(1, 1));
        percolation.open(2,6);
        StdOut.println("isfull:" + percolation.isFull(2,6));
        percolation.open(5, 1);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("isfull:" + percolation.isFull(5, 1));
        percolation.open(3, 3);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("isfull:" + percolation.isFull(3, 3));
        percolation.open(2, 3);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("isfull:" + percolation.isFull(2, 3));
        percolation.open(3, 3);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("isfull:" + percolation.isFull(3, 3));
//        percolation.open(6, 6);
        StdOut.println("opensites = " + percolation.numberOfOpenSites());
//        StdOut.println("isfull:" + percolation.isFull(4, 3));
        percolation.open(4, 3);
        StdOut.println("opensites = " + percolation.numberOfOpenSites());
//        percolation.open(8, 8);
//        StdOut.println("opensites = " + percolation.numberOfOpenSites());
        StdOut.println("ispercolates: " + percolation.percolates());
//        for (int i = 1; i <= n; i++)
//            for (int j = 1; j <= n; j++)
//                StdOut.println("isfull(" + i + ", " + j + ") = " + percolation.isFull(i, j));
//        percolation.open(2, 1);
//        for (int i = 1; i <= n; i++)
//            for (int j = 1; j <= n; j++)
//                StdOut.println("isfull(" + i + ", " + j + ") = " + percolation.isFull(i, j));
    }
}
