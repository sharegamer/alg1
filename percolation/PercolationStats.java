import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private double[] average;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private int nu;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();
        nu = n;
        average = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation model = new Percolation(n);

            while (!model.percolates()) {
                int j = StdRandom.uniformInt(n * n) + 1;
                int k = j % n;
                if (k == 0)
                    k = n;
                if (!model.isOpen((int) Math.ceil((double) j / n), k)) {
                    model.open((int) Math.ceil((double) j / n), k);

                }
            }
            average[i] = (double) model.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(average);
        stddev = StdStats.stddev(average);
        confidenceLo = (mean - 1.96 * stddev / Math.sqrt((double) nu));
        confidenceHi = (mean + 1.96 * stddev / Math.sqrt((double) nu));
    }

    // sample mean of percolation threshold
    public double mean() {

        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }


    // test client (see below)
    public static void main(String[] args) {
        int i, j;
        i = Integer.parseInt(args[0]);
        j = Integer.parseInt(args[1]);
        PercolationStats a = new PercolationStats(i, j);
        System.out.println("mean                    = " + a.mean());
        System.out.println("stddev                  = " + a.stddev());
        System.out.println("95% confidence interval = [" + a.confidenceLo()
                                   + ", " + a.confidenceHi() + "]");
    }
}
