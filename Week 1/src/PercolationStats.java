import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int trials;
    private double[] percolations;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.trials = trials;
        percolations = new double[trials];

        for (int i = 0; i < trials; i++) {
            int pi, pj, x = 0;
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                do {
                    pi = StdRandom.uniform(n) + 1;
                    pj = StdRandom.uniform(n) + 1;
                } while (percolation.isOpen(pi, pj));
                percolation.open(pi, pj);
                x++;
            }

            percolations[i] = 1.0 * x / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolations);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
