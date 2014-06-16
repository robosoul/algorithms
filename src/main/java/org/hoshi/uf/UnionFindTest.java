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
public class UnionFindTest {
    public static final Logger log = LoggerFactory.getLogger(UnionFindTest.class);

    public static final int MAX_NUM_OF_ELEMENTS = 10;

    public static void main(String[] args) {
        final UF uf = new QuickFindUF(MAX_NUM_OF_ELEMENTS);

        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(3, 4);
        uf.union(5, 6);
        uf.union(7, 8);
        uf.union(7, 9);
        uf.union(2, 8);
        uf.union(0, 5);
        uf.union(1, 9);

        System.out.println(uf.count());
    }
}