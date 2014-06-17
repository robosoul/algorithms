/**
 * Copyright (C) 2014 Luka Obradovic.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        this.n = n;

        top = 0;
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
        if (!valid(i, j)) {
            throw new IndexOutOfBoundsException("(" + i + ", " + j + ")");
        }

        // get current site's index
        final int current = xzTo1D(i, j);

        if (sites[current]) {
            return; // already open, don't do anything
        }

        // let's open it
        sites[current] = true;

        // check if current site can be connected to the virtual top, otherwise
        // check site above current one
        if (i == 1) {
            uf.union(top, current);
        } else if (valid(i - 1, j) && isOpen(i - 1, j)) {
            uf.union(xzTo1D(i - 1, j), current);
        }

        // check if current site can be connected to the virtual bottom, otherwise
        // check site bellow current one
        if (i == n) {
            uf.union(bottom, current);
        } else if (valid(i + 1, j) && isOpen(i + 1, j)) {
            uf.union(xzTo1D(i + 1, j), current);
        }

        // check site left of current one
        if (valid(i, j - 1) && isOpen(i, j - 1)) {
            uf.union(xzTo1D(i, j - 1), current);
        }

        // check site right of current one
        if (valid(i, j + 1) && isOpen(i, j + 1)) {
            uf.union(xzTo1D(i, j + 1), current);
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
        if (!valid(i, j)) {
            throw new IndexOutOfBoundsException("(" + i + ", " + j + ")");
        }

        return sites[xzTo1D(i, j)];
    }

    /**
     * Returns true if site (row i, column j) full.
     *
     * @param i row
     * @param j column
     * @return true if site (row i, column j) full.
     */
    public boolean isFull(final int i, final int j) {
        if (!valid(i, j)) {
            throw new IndexOutOfBoundsException("(" + i + ", " + j + ")");
        }

        return uf.connected(bottom, xzTo1D(i, j));
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
    private int xzTo1D(final int i, final int j) {
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
}