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


import java.util.Random;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class HedgeMaze extends AbstractMaze {
    public static final Random RNDGEN = new Random();

    private int start;
    private int goal;

    private boolean[] cells;

    public HedgeMaze(final int n, final int m) {
        super(n, m);
    }

    @Override
    protected void init() {
        start = xyTo1D(1, 0);
        goal  = xyTo1D(n - 2, m - 1);

        cells = new boolean[n * m];

        cells[start] = true;
        cells[goal]  = true;
    }

    @Override
    protected void generate() {
        while (!connected(start, goal)) {
            int row;
            int col;
            do {
                row = RNDGEN.nextInt(n - 1) + 1;
                col = RNDGEN.nextInt(m - 1) + 1;
            } while (!canOpen(row, col));

            open(row, col);
        }
    }

    private void open(final int i, final int j) {
        valid(i, j);

        if (isOpen(i, j)) {
            return;
        }

        if (!canOpen(i, j)) {
            return;
        }

        int current = xyTo1D(i, j);

        // let's open it
        cells[current] = true;

        if (checkNorth(i, j)) {
            connect(xyTo1D(i - 1, j), current);
        }

        if (checkSouth(i, j)) {
            // otherwise check site bellow current one
            connect(xyTo1D(i + 1, j), current);
        }

        // check site left of current one
        if (checkWest(i, j)) {
            connect(xyTo1D(i, j - 1), current);
        }

        // check site right of current one
        if (checkEast(i, j)) {
            connect(xyTo1D(i, j + 1), current);
        }
    }

    /**
     * Returns true if specified cell is open.
     * @param i row
     * @param j column
     * @return true if specified cell is open.
     */
    private boolean isOpen(final int i, final int j) {
        return cells[xyTo1D(i, j)];
    }

    /**
     * Returns true if specified cell is closed.
     * @param i row
     * @param j column
     * @return true if specified cell is closed.
     */
    private boolean isClosed(final int i, final int j) {
        return !isOpen(i, j);
    }

    private boolean canOpen(final int i, final int j) {
        return valid(i, j) && !onOuterWall(i, j) && isClosed(i, j) && !unsafe(i, j);
    }

    private boolean unsafe(final int i, final int j) {
        return (checkWest(i, j) && checkNorthWest(i, j) && checkNorth(i, j))
                || (checkNorth(i, j) && checkNorthEast(i, j) && checkEast(i, j))
                || (checkEast(i, j)  && checkSouthEast(i, j) && checkSouth(i, j))
                || (checkSouth(i, j) && checkSouthWest(i, j) && checkWest(i, j));
    }

    private boolean onOuterWall(final int i, final int j) {
        return (i == 0) || (i == n - 1) || (j == 0) || (j == m - 1);
    }

    /**
     * Returns true if site directly above current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site directly above current site is valid and open.
     */
    private boolean checkNorth(final int i, final int j) {
        return valid(i - 1, j) && isOpen(i - 1, j);
    }

    private boolean checkNorthEast(final int i, final int j) {
        return valid(i - 1, j + 1) && isOpen(i - 1, j + 1);
    }

    private boolean checkNorthWest(final int i, final int j) {
        return valid(i - 1, j - 1) && isOpen(i - 1, j - 1);
    }

    /**
     * Returns true if site directly below current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site directly below current site is valid and open.
     */
    private boolean checkSouth(final int i, final int j) {
        return valid(i + 1, j) && isOpen(i + 1, j);
    }

    private boolean checkSouthEast(final int i, final int j) {
        return valid(i + 1, j + 1) && isOpen(i + 1, j + 1);
    }

    private boolean checkSouthWest(final int i, final int j) {
        return valid(i + 1, j - 1) && isOpen(i + 1, j - 1);
    }

    /**
     * Returns true if site left of current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site left of current site is valid and open.
     */
    private boolean checkWest(final int i, final int j) {
        return valid(i, j - 1) && isOpen(i, j - 1);
    }

    /**
     * Returns true if site right of current site is valid and open.
     *
     * @param i row
     * @param j col
     * @return true if site right of current site is valid and open.
     */
    private boolean checkEast(final int i, final int j) {
        return valid(i, j + 1) && isOpen(i, j + 1);
    }

    @Override
    public void print() {
        int current;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                current = xyTo1D(i, j);

                if (current == start) {
                    System.out.print('s');
                } else if (current == goal) {
                    System.out.print('g');
                } else {
                    System.out.print((cells[current]) ? ' ' : '#');
                }
            }

            System.out.println();
        }
    }
}