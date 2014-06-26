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
package org.hoshi.collections.stacks.application;

import org.hoshi.collections.stacks.LinkedStack;
import org.hoshi.collections.stacks.Stack;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class BinaryTransformer {
    public String transform(int n) {
        final Stack<Integer> bin = new LinkedStack<Integer>();

        while (n > 0) {
            bin.push(n % 2);
            n /= 2;
        }

        final StringBuilder sb = new StringBuilder();
        for (Integer i : bin) {
            sb.append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BinaryTransformer().transform(50));
    }
}