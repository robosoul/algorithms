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
package org.hoshi.uf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class WeightedQuickUnionUF extends QuickUnionUF {
    public static final Logger log =
            LoggerFactory.getLogger(WeightedQuickUnionUF.class);

    // represents size of each tree
    private final int[] sz;

    protected WeightedQuickUnionUF(final int n) {
        super(n);

        sz = new int[n];
        for (int i = 0; i < n; ++i) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(final int p, final int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP != rootQ) {
            if (sz[rootP] < sz[rootQ]) {
                id[rootP] = rootQ;
                sz[rootQ] += sz[rootP];
            } else {
                id[rootQ] = rootP;
                sz[rootP] += sz[rootQ];
            }

            --count;
        }
    }
}