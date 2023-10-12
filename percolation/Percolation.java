import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF qu;
    private WeightedQuickUnionUF quf;
    private boolean[][] open;
    private int num;
    private int numopen = 0;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        qu = new WeightedQuickUnionUF(n * n + 2);
        quf = new WeightedQuickUnionUF(n * n + 1);
        open = new boolean[n + 1][n + 1];
        num = n;
        for (int i = 1; i <= n; i++) {
            qu.union(0, i);
            qu.union(n * n + 1, n * n - n + i);
            quf.union(0, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();

        int pos = (row - 1) * num + col;
        if (open[row][col])
            return;
        else {
            numopen++;
            open[row][col] = true;
            if (row != 1) {
                if (isOpen(row - 1, col)) {
                    qu.union(pos, pos - num);
                    quf.union(pos, pos - num);
                }
            }
            if (row != num) {
                if (isOpen(row + 1, col)) {
                    qu.union(pos, pos + num);
                    quf.union(pos, pos + num);
                }
            }
            if (col != 1) {
                if (isOpen(row, col - 1)) {
                    qu.union(pos, pos - 1);
                    quf.union(pos, pos - 1);
                }
            }
            if (col != num) {
                if (isOpen(row, col + 1)) {
                    qu.union(pos, pos + 1);
                    quf.union(pos, pos + 1);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();
        return open[row][col];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > num || col < 1 || col > num)
            throw new IllegalArgumentException();
        if (isOpen(row, col))
            return quf.find((row - 1) * num + col) == quf.find(0);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numopen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (num == 1)
            return isOpen(1, 1);
        return qu.find(0) == qu.find(num * num + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
