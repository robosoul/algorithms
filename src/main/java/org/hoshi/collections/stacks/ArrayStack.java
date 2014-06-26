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
 * Resizing array implementation of stack.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class ArrayStack<E> implements Stack<E> {
    private static final int INITIAL_CAPACITY = 11;

    private E[] array;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.array = (E[]) new Object[INITIAL_CAPACITY];
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

        E e = array[n - 1];
        array[n - 1] = null;                              // to avoid loitering
        --n;

        if (n > 0 && n == array.length / 4) {
            resize(array.length / 2);
        }

        return e;
    }

    @Override
    public void push(final E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        if (n == array.length) {
            resize(2 * array.length);
        }

        array[n++] = e;
    }

    @Override
    public int size() {
        return n;
    }

    @SuppressWarnings("unchecked")
    private void resize(final int capacity) {
        assert capacity > 0;

        final E[] temp = (E[]) new Object[capacity];

        for (int i = 0; i < n; ++i) {
            temp[i] = array[i];
        }

        array = temp;
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