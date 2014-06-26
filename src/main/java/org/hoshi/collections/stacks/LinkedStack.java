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
 * A linked list stack implementation.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class LinkedStack<E> implements Stack<E> {
    private Node<E> head;
    private int size;

    public LinkedStack() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null; // OR size() == 0;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return head.data;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        final E data = head.data;
        head = head.next;
        --size;

        return data;
    }

    @Override
    public void push(final E e) {
        head = new Node<E>(e, head);
        ++size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (E data : this) {
            sb.append(data).append(" ");
        }

        return sb.toString();
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node() {
            this(null, null);
        }

        public Node(final E data) {
            this(data, null);
        }

        public Node(final E data, final Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            final E e = current.data;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}