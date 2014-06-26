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
package org.hoshi.collections.stacks;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class BoundedStack<E> extends FixedCapacityArrayStack<E> {
    public BoundedStack() {
        super(10);
    }

    public BoundedStack(final int capacity) {
        super(capacity);
    }

    @Override
    public void push(final E e) {
        if (n == array.length) {
            compress();
        }

        array[n++] = e;
    }

    private void compress() {
        for (int i = 0; i < n - 1; ++i) {
            array[i] = array[i + 1];
        }

        --n;
    }

    public static void main(String[] args) {
        final Stack<String> history = new BoundedStack<String>(3);

        history.push("www.google.com");
        history.push("www.gmail.com");
        history.push("http://en.wikipedia.org/wiki/Stack_%28abstract_data_type%29");
        print(history);

        history.push("http://algs4.cs.princeton.edu/13stacks");
        history.push("http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle");
        print(history);
    }

    private static void print(final Stack<String> history) {
        System.out.println();
        System.out.println("My browsing history");
        System.out.println("-------------------");
        for (String page : history) {
            System.out.println(page);
        }
    }
}