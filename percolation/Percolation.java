
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    WeightedQuickUnionUF qu;
    WeightedQuickUnionUF quf;
    public Percolation(int n)
    {
        if(n<=0)
            throw new IllegalArgumentException();
        qu=new WeightedQuickUnionUF(n*n+2);
        quf=new WeightedQuickUnionUF(n*n+1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(1<row && row<n)
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)

    // is the site (row, col) full?
    public boolean isFull(int row, int col)

    // returns the number of open sites
    public int numberOfOpenSites()

    // does the system percolate?
    public boolean percolates()

    // test client (optional)
    public static void main(String[] args)
}
