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
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class QuickUnionUF extends AbstractUF {
    public QuickUnionUF(final int n) {
        super(n);
    }

    protected QuickUnionUF(final int n, final int fillWith) {
        super(n, fillWith);
    }

    @Override
    public void union(final int p, final int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP != rootQ) {
            id[rootP] = rootQ;
            --count;
        }
    }

    @Override
    public int find(int i) {
        while (i != id[i]) {
            i = id[i];
        }

        return i;
    }

    @Override
    public int count() {
        return count;
    }
}