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


/**
 * "My" version of ranked disjoint set (with path compression).
 *
 *      0   1   2   3   4   5
 * id [-1, -1, -1, -1, -1, -1]
 *
 * union(2, 1)
 * id [-1, -2,  1, -1, -1, -1]
 *
 * union(3, 0)
 * id [-2, -2,  1,  0, -1, -1]
 *
 * union(1, 0)
 * id [-4,  0,  0,  0, -1, -1]
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class RankedQuickUnionWithPathCompressionUF extends RankedQuickUnionUF {
    public RankedQuickUnionWithPathCompressionUF(final int n) {
        super(n);
    }

    @Override
    public int find(int i) {
        int root = i;

        while (id[root] > 0) {
            root = id[root];
        }

        int tmp;
        while (id[i] > 0) {
            tmp = id[i];
            id[i] = root;
            i = tmp;
        }

        return root;
    }
}
