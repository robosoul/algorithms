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

import org.hoshi.uf.UF;
import org.hoshi.uf.WeightedQuickUnionPathCompressionUF;

import java.util.Random;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class MyMaze {

    public static final byte NORTH = 1; // (00000001) cell with north wall only
    public static final byte EAST  = 2; // (00000010) cell with east  wall only
    public static final byte SOUTH = 4; // (00000100) cell with south wall only
    public static final byte WEST  = 8; // (00001000) cell with west  wall only

    // 15 = UP | RIGHT | SOUTH | LEFT (closed cell)
    // 12 = NORTH | SOUTH | WEST      (cell open on east side)
    //
    // cell &= ~EAST                  (remove east wall)
    // cell |= SOUTH                  (add south wall)
    // (cell & WEST == 0)             (cell has no west wall

    private final int n;
    private final int m;

    private final byte[] cells;
    private final UF uf;

    protected MyMaze(final int n, final int m) {
        this.n = n;
        this.m = m;

        cells = new byte[n * m];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = NORTH | EAST | SOUTH | WEST;
        }

        uf = new WeightedQuickUnionPathCompressionUF(n * m);
    }

    public static MyMaze generate(final int n, final int m) {
        final MyMaze maze = new MyMaze(n, m);
        return maze;
    }
}