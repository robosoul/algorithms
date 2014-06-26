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
 *  The <tt>QuickFindUF</tt> class represents a union-find data structure.
 *  It supports the <em>union</em> and <em>find</em> operations, along with
 *  methods for determining whether two objects are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses quick find.
 *  Initializing a data structure with <em>N</em> objects takes linear time.
 *  Afterwards, <em>find</em>, <em>connected</em>, and <em>count</em>
 *  takes constant time but <em>union</em> takes linear time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class QuickFindUF extends AbstractUF {
    public QuickFindUF(final int n) {
        super(n);
    }

    @Override
    public void union(final int p, final int q) {
        if (connected(p, q)) {
            return;
        }

        int idp = id[p];
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == idp) {
                id[i] = id[q];
            }
        }

        --count;
    }

    @Override
    public int find(final int p) {
        return id[p];
    }

    @Override
    public int count() {
        return count;
    }
}