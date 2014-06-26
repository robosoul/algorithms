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
 * Quick-union with path compression. Modify QuickUnionUF.java to include path
 * compression, by adding a loop to find() that links every sie on the path from
 * p to the root. Give a sequence of input pairs that causes this method to
 * produce a path of length 4.
 *
 * Note: the amortized cost per operation for this algorithm is known to be
 *       logarithmic.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class QuickUnionPathCompressionUF extends QuickUnionUF {
    public QuickUnionPathCompressionUF(final int n) {
        super(n);
    }

    @Override
    public int find(final int i) {
        int root = i;
        while (root != id[root]) {
            root = id[root];
        }

        // make i-th element directly linked to the root.
        id[i] = root;

        return root;
    }
}