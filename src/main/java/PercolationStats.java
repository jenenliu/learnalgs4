import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double  mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform trails independent experiments on an n-by-n grid
    public PercolationStats(int n, int trails)
    {
        if (n <= 0 || trails <= 0)
            throw new IllegalArgumentException();

        final double[] xs;
        xs = new double[trails];
        for (int i = 0; i < trails; i++)
        {
            Percolation percolation = new Percolation(n);
            double count = 0;
            while (!percolation.percolates())
            {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (percolation.isOpen(row, col))
                    continue;
//                StdOut.println("row = " + row + ", col = " + col);
                percolation.open(row, col);
                count = count + 1;
            }
            double threshold = count / (n*n);
            xs[i] = threshold;
//            StdOut.println("percolates after " + count + ", threshold is " + threshold);
        }

        mean = StdStats.mean(xs);
        if (xs.length == 1)
            stddev = 1;
        else
            stddev = StdStats.stddev(xs);
        double sqrtt = Math.sqrt(trails);
        double STDDEVRATE = 1.96;
        confidenceLo = mean - (STDDEVRATE * stddev) / sqrtt;
        confidenceHi = mean + (STDDEVRATE * stddev) / sqrtt;
    }

    public double mean()
    {
        return mean;
    }

    public double stddev()
    {
        return stddev;
    }

    public double confidenceLo()
    {
        return confidenceLo;
    }

    public double confidenceHi()
    {
        return confidenceHi;
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, trails);
        StdOut.println("mean = " + p.mean());
        StdOut.println("stddev = " + p.stddev());
        StdOut.println("95% confidence interval = " + "[" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }
}
