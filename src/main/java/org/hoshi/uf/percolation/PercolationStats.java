package org.hoshi.uf.percolation;

import java.util.Random;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class PercolationStats {
    public static final Random RNDGEN = new Random();
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    /**
     * Perform T independent computational experiments on an N-by-N grid.
     *
     * @param N grid dimensions
     * @param T num of test
     */
    public PercolationStats(final int N, final int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("invalid dimensions: " + N);
        }

        if (T <= 0) {
            throw new IllegalArgumentException("invalid num of test: " + T);
        }

        final double[] xs = new double[T];

        double total = 0.0;
        for (int i = 0; i < T; ++i) {
            xs[i] = run(N);
            total += xs[i];
        }

        // calculation mean
        mean = total / T;

        // calculation standard deviation
        total = 0.0;
        for (double x : xs) {
            total += (x - mean) * (x - mean);
        }

        stddev = Math.sqrt(total / (T - 1));

        confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
        confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
    }

    private double run(final int N) {
        final Percolation percolation = new Percolation(N);

        final int numOfSites = N * N;

        int open = 0;

        int row;
        int col;
        while (!percolation.percolates()) {
            do {
                row = RNDGEN.nextInt(N) + 1;
                col = RNDGEN.nextInt(N) + 1;
            } while (percolation.isOpen(row, col));

            percolation.open(row, col);
            ++open;
        }

        return (double) open / (double) numOfSites;
    }

    /**
     * Returns mean of percolation threshold.
     * @return mean of percolation threshold.
     */
    public double mean() {
        return mean;
    }

    /**
     * Returns standard deviation of percolation threshold.
     * @return standard deviation of percolation threshold.
     */
    public double stddev() {
        return stddev;
    }

    /**
     * Returns lower bound of the 95% confidence interval.
     * @return lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * Returns upper bound of the 95% confidence interval.
     * @return upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        final PercolationStats stats = new PercolationStats(200, 100);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());

    }
}
