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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Fixed capacity implementation of stack.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class FixedCapacityArrayStack<E> implements Stack<E> {
    // current number of data on stack
    private int n;
    private E[] array;

    @SuppressWarnings("unchecked")
    public FixedCapacityArrayStack(final int capacity) {
        this.array = (E[]) new Object[capacity];
        this.n = 0;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return array[n - 1];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        final E e = array[n - 1];
        array[--n] = null;                 // to avoid loitering

        return e;
    }

    @Override
    public void push(final E e) {
        if (n < array.length) {
            array[n++] = e;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (E data : this) {
            sb.append(data).append(" ");
        }

        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<E> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}