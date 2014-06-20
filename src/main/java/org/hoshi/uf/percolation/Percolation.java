package org.hoshi.uf.percolation;

import org.hoshi.uf.UF;
import org.hoshi.uf.WeightedQuickUnionPathCompressionUF;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class Percolation {
    private final UF uf;

    private final int top;
    private final int bottom;

    private final int n;
    private final boolean[] sites;

    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid dimensions: " + n);
        }

        this.n = n;

        // virtual top index
        top = 0;

        // virtual bottom index
        bottom = n * n + 1;

        // two additional sites (virtual top and virtual bottom)
        uf = new WeightedQuickUnionPathCompressionUF(n * n + 2);

        // all sites are blocked (false) by default
        sites = new boolean[n * n + 2];

        // virtual top site is always open
        sites[top] = true;

        // virtual bottom site is always open
        sites[bottom] = true;
    }

    /**
     * Opens site (row i, column j) if it is not already.
     *
     * @param i row
     * @param j column
     */
    public void open(final int i, final int j) {
        validate(i, j);

        // get current site's index
        final int current = xyTo1D(i, j);

        if (sites[current]) {
            return; // already open, don't do anything
        }

        // let's open it
        sites[current] = true;

        // check if current site can be connected to the virtual top
        if (i == 1) {
            uf.union(top, current);
        } else if (checkAbove(i, j)) {
            // otherwise check site above current one
            uf.union(xyTo1D(i - 1, j), current);
        }

        // check if current site can be connected to the virtual bottom
        if (i == n) {
            uf.union(bottom, current);
        } else if (checkBelow(i, j)) {
            // otherwise check site bellow current one
            uf.union(xyTo1D(i + 1, j), current);
        }

        // check site left of current one
        if (checkLeft(i, j)) {
            uf.union(xyTo1D(i, j - 1), current);
        }

        // check site right of current one
        if (checkRight(i, j)) {
            uf.union(xyTo1D(i, j + 1), current);
        }
    }

    /**
     * Returns true if site (row i, column j) is open.
     *
     * @param i row
     * @param j column
     * @return true if site (row i, column j) is open.
     */
    public boolean isOpen(final int i, final int j) {
        validate(i, j);

        return sites[xyTo1D(i, j)];
    }

    /**
     * Returns true if site (row i, column j) full.
     *
     * @param i row
     * @param j column
     * @return true if site (row i, column j) full.
     */
    public boolean isFull(final int i, final int j) {
        validate(i, j);

        return uf.connected(top, xyTo1D(i, j));
    }

    /**
     * Returns true if the system percolate.
     *
     * @return true if the system percolate.
     */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    /**
     * Transforms 2D coordinates to 1D.
     *
     * @param i row
     * @param j column
     * @return transformed 2D coordinates to 1D.
     */
    private int xyTo1D(final int i, final int j) {
        return (i - 1) * n + j;
    }

    /**
     * Returns true if both {@code i} and {@code j} are >= 1 and <= n.
     *
     * @param i row
     * @param j column
     * @return true if both {@code i} and {@code j} are >= 1 and <= n.
     */
    private boolean valid(final int i, final int j) {
        return (i >= 1 && i <= n) && (j >= 1 && j <= n);
    }

    /**
     * Calls {@code valid(i, j)} and throws {@code IndexOutOfBoundsException} if
     * coordinates are invalid.
     *
     * @param i row
     * @param j column
     */
    private void validate(final int i, final int j) {
        if (!valid(i, j)) {
            throw new IndexOutOfBoundsException("Invalid coordinates (" + i + ", " + j + ")");
        }
    }

    /**
     * Returns true if site directly above current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site directly above current site is valid and open.
     */
    private boolean checkAbove(final int i, final int j) {
        return valid(i - 1, j) && isOpen(i - 1, j);
    }

    /**
     * Returns true if site directly below current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site directly below current site is valid and open.
     */
    private boolean checkBelow(final int i, final int j) {
        return valid(i + 1, j) && isOpen(i + 1, j);
    }

    /**
     * Returns true if site left of current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site left of current site is valid and open.
     */
    private boolean checkLeft(final int i, final int j) {
        return valid(i, j - 1) && isOpen(i, j - 1);
    }

    /**
     * Returns true if site right of current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site right of current site is valid and open.
     */
    private boolean checkRight(final int i, final int j) {
        return valid(i, j + 1) && isOpen(i, j + 1);
    }
}