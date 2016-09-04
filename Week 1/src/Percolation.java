import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean percolates;
    private WeightedQuickUnionUF uf;

    private boolean[] open;
    private boolean[] topConnected;
    private boolean[] bottomConnected;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        uf = new WeightedQuickUnionUF(n * n);
        bottomConnected = new boolean[n * n];
        topConnected = new boolean[n * n];
        open = new boolean[n * n];
        this.n = n;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        validateIndices(i, j);

        open[index(i, j)] = true;
        boolean topStatus = false;
        boolean bottomStatus = false;

        if (i == n) {
            bottomStatus = true;
        }
        if (i == 1) {
            topStatus = true;
        }

        if (i > 1 && isOpen(i - 1, j)) {
            bottomStatus = bottomStatus || bottomConnected[uf.find(index(i - 1, j))];
            topStatus = topStatus || topConnected[uf.find(index(i - 1, j))];
            uf.union(index(i, j), index(i - 1, j));
        }
        if (i < n && isOpen(i + 1, j)) {
            bottomStatus = bottomStatus || bottomConnected[uf.find(index(i + 1, j))];
            topStatus = topStatus || topConnected[uf.find(index(i + 1, j))];
            uf.union(index(i, j), index(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            bottomStatus = bottomStatus || bottomConnected[uf.find(index(i, j - 1))];
            topStatus = topStatus || topConnected[uf.find(index(i, j - 1))];
            uf.union(index(i, j), index(i, j - 1));
        }
        if (j < n && isOpen(i, j + 1)) {
            bottomStatus = bottomStatus || bottomConnected[uf.find(index(i, j + 1))];
            topStatus = topStatus || topConnected[uf.find(index(i, j + 1))];
            uf.union(index(i, j), index(i, j + 1));
        }

        int root = uf.find(index(i, j));
        if (bottomStatus) {
            bottomConnected[root] = true;
        }
        if (topStatus) {
            topConnected[root] = true;
        }

        if (topConnected[root] && bottomConnected[root]) {
            percolates = true;
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        return open[index(i, j)];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validateIndices(i, j);
        return isOpen(i, j) && topConnected[uf.find(index(i, j))];
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    private int index(int i, int j) {
        return (i - 1) * n + j - 1;
    }

    private void validateIndices(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
