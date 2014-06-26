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


import java.util.Random;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class UnionFindTest {
    public static final Random RNDGEN = new Random();

    private final UF uf;
    private long time;
    private int n;

    public UnionFindTest(final int type, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        this.n = n;

        if (type == 0) {
            uf = new QuickFindUF(n);
        } else if (type == 1) {
            uf = new QuickUnionUF(n);
        } else if (type == 2) {
            uf = new QuickUnionRecursiveFindUF(n);
        } else if (type == 3) {
            uf = new QuickUnionPathCompressionUF(n);
        } else if (type == 4) {
            uf = new WeightedQuickUnionUF(n);
        } else if (type == 5) {
            uf = new WeightedQuickUnionPathCompressionUF(n);
        } else if (type == 6) {
            uf = new WeightedQuickUnionRecursivePathCompressionUF(n);
        } else if (type == 7) {
            uf = new RankedQuickUnionUF(n);
        } else if (type == 8) {
            uf = new RankedQuickUnionWithPathCompressionUF(n);
        } else {
            uf = null;
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }

    public void run() {
        final long start = System.currentTimeMillis();

        int i;
        int j;
        while (uf.count() > 1) {
            i = RNDGEN.nextInt(n);
            j = RNDGEN.nextInt(n);

            if (!uf.connected(i, j)) {
                uf.union(i, j);
            }

            //System.out.println(i + " " + j);
        }

        final long end = System.currentTimeMillis();

        time = end - start;
    }

    public String name() {
        return uf.getClass().getSimpleName();
    }

    private long time() {
        return time;
    }

    public static void main(final String[] args) {
        final int n = 1000000;

        // START: warm-up
        for (int i = 0; i < n; ++i) {
            // let's warm-up jvm
        }

        UnionFindTest test;
        for (int i = 4; i < 9; ++i) {
            test = new UnionFindTest(i, n);
            test.run();
        }
        // END: warm-up


        for (int i = 4; i < 9; ++i) {
            test = new UnionFindTest(i, n);
            test.run();

            String name = String.format("%-50s", test.name()).replace(' ', '.').replaceFirst(test.name() + ".", test.name() + " ");
            System.out.printf("%s %dms (%d)%n", name, test.time(), n);
        }
    }
}