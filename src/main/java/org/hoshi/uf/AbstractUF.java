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
public abstract class AbstractUF implements UF {
    public static final Logger log = LoggerFactory.getLogger(AbstractUF.class);

    // represents the number of sets {0,5,6} {1,2,7,8,9} {4,4}
    protected int count;

    // represents the actual connections between objects
    protected final int[] id;

    protected AbstractUF(final int n) {
        this.count = n;
        this.id = new int[n];

        for (int i = 0; i < id.length; ++i) {
            id[i] = i;
        }
    }

    @Override
    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }
}