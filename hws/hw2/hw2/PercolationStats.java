package hw2;

import java.util.Random;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int exptCount;
    private double[] sample;
    private static final long SEED = 2873163;
    private static final Random RANDOM = new Random(SEED);

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }

        this.exptCount = T;
        this.sample = new double[exptCount];
        for (int i = 0; i < this.exptCount; i ++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int row = RANDOM.nextInt(N);
                int col = RANDOM.nextInt(N);
                if (!perc.isOpen(row, col)) perc.open(row, col);
            }
            this.sample[i] = (double)perc.numberOfOpenSites() / ((double) N * N );
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.sample);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.sample);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(exptCount);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(exptCount);
    }

    public static void main(String[] args) {
        PercolationStats percStats = new PercolationStats(5, 30, new PercolationFactory());
        System.out.println(percStats.stddev());
    }
}
