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
 * Modify WeightedQuickUnionUF.java Note: The amortized cost per operation for
 * this algorithm is known to be bounded by a function known as the inverse
 * Ackermann function and is less than 5 for any conceivable practical values of N.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class WeightedQuickUnionPathCompressionUF extends WeightedQuickUnionUF {
    public static final Logger log = LoggerFactory.getLogger(
            WeightedQuickUnionPathCompressionUF.class);

    public WeightedQuickUnionPathCompressionUF(final int n) {
        super(n);
    }

    @Override
    public int find(final int i) {
        int f = i;
        while (f != id[f]) {
            f = id[f];
        }

        int tmp;
        while (i != f) {
            tmp = id[i];
            id [i] = f;
            f = tmp;
        }

        return f;
    }
}