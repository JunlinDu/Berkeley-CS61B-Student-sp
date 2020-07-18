package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

/*
*  Performance requirements:
*  - The constructor should take time proportional to N2;
*  - all methods should take constant time plus a constant
*    number of calls to the union-find methods union(),
*    find(), connected(), and count().
* */

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF disjointSet;
    private WeightedQuickUnionUF disjointSetNoButtom;
    private int openSiteCount;
    private int bottomNode;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.grid = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                this.grid[row][col] = false;
;            }
        }

        this.disjointSet = new WeightedQuickUnionUF(N * N + 2);
        this.disjointSetNoButtom = new WeightedQuickUnionUF(N * N + 2);
        this.bottomNode = N * N + 1;
        this.openSiteCount = 0;
    }

    private int twoDToUF(int row, int col) {
        return row * this.grid.length + col + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!this.grid[row][col]) {
            this.grid[row][col] = true;
            if (row == 0) {
                disjointSet.union(0, twoDToUF(row, col));
                disjointSetNoButtom.union(0, twoDToUF(row, col));
            }

            if (row == this.grid.length - 1) {
                disjointSet.union(bottomNode, twoDToUF(row, col));
            }

            if (row > 0 && isOpen(row - 1, col)) {
                disjointSet.union(twoDToUF(row, col), twoDToUF(row - 1, col));
                disjointSetNoButtom.union(twoDToUF(row, col), twoDToUF(row - 1, col));
            }

            if (col > 0 && isOpen(row, col - 1)) {
                disjointSet.union(twoDToUF(row, col), twoDToUF(row, col - 1));
                disjointSetNoButtom.union(twoDToUF(row, col), twoDToUF(row, col - 1));
            }

            if (row < this.grid.length - 1 && isOpen(row + 1, col)) {
                disjointSet.union(twoDToUF(row, col), twoDToUF(row + 1, col));
                disjointSetNoButtom.union(twoDToUF(row, col), twoDToUF(row + 1, col));
            }

            if (col < this.grid.length - 1 && isOpen(row, col + 1)) {
                disjointSet.union(twoDToUF(row, col), twoDToUF(row, col + 1));
                disjointSetNoButtom.union(twoDToUF(row, col), twoDToUF(row, col + 1));
            }

            this.openSiteCount++;
        }
    }

    // checks if the site (row, col) is open
    public boolean isOpen(int row, int col) {
        return this.grid[row][col];
    }

    // checks if the site (row, col) is full
    public boolean isFull(int row, int col) {
        return  this.disjointSet.connected(twoDToUF(row, col), 0) &&
                this.disjointSetNoButtom.connected(twoDToUF(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.openSiteCount;
    }

    // checks if the system percolate
    public boolean percolates() {
        return this.disjointSet.connected(0, bottomNode);
    }


    public static void main(String[] args) {
        Percolation p = new Percolation(6);
        System.out.println(p.twoDToUF(4,5));
        System.out.println();
    }
}
