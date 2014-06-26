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
package org.hoshi.uf.maze;

import org.hoshi.uf.RankedQuickUnionWithPathCompressionUF;
import org.hoshi.uf.UF;
import org.hoshi.uf.WeightedQuickUnionPathCompressionUF;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public abstract class AbstractMaze implements Maze {
    protected final int n;
    protected final int m;

    private final UF uf;

    public AbstractMaze(final int n, final int m) {
        this.n = n;
        this.m = m;

        uf = new RankedQuickUnionWithPathCompressionUF(n * m);

        init();
        generate();
    }

    protected abstract void init();

    protected abstract void generate();

    protected void connect(int p, int q) {
        uf.union(p, q);
    }

    protected boolean connected(int p, int q) {
        return uf.connected(p, q);
    }

    /**
     * Transforms 2D coordinates to 1D.
     *
     * @param i row
     * @param j column
     * @return transformed 2D coordinates to 1D.
     */
    protected int xyTo1D(final int i, final int j) {
        return i * m + j;
    }

    /**
     * Returns true if both {@code i} and {@code j} are >= 1 and <= n.
     *
     * @param i row
     * @param j column
     * @return true if both {@code i} and {@code j} are >= 1 and <= n.
     */
    protected boolean valid(final int i, final int j) {
        return (i >= 0 && i < n) && (j >= 0 && j < m);
    }

    /**
     * Calls {@code valid(i, j)} and throws {@code IndexOutOfBoundsException} if
     * coordinates are invalid.
     *
     * @param i row
     * @param j column
     */
    protected void validate(final int i, final int j) {
        if (!valid(i, j)) {
            throw new IndexOutOfBoundsException("Invalid coordinates (" + i + ", " + j + ")");
        }
    }
}