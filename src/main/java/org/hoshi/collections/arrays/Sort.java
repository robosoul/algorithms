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
package org.hoshi.collections.arrays;

import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class Sort {
    /**
     * Sort an array using "Shell sort" algorithm.
     *
     * @param a array to be sorted
     */
    public static <T extends Comparable<T>> void shell(final T[] a) {
        final int n = a.length;

        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; ++i) {
                int j = i;

                while (j >= h && less(a[j], a[j - h])) {
                    swap(a, j, j - 1);
                    j -= h;
                }
            }

            h /= 3;
        }
    }

    /**
     * Sort an array using "Insertion sort" algorithm.
     *
     * Insertion sort has linear time when sorting partially sorted array, but
     * in worst case (reversed order) it has quadratic with N compares and N exchanges.
     *
     * @param a array to be sorted
     */
    public static <T extends Comparable<T>> void insertion(final T[] a) {
        final int n = a.length;

        for (int i = 0; i < n; ++i) {
            int j = i;

            while (j > 0 && less(a[j], a[j - 1])) {
                swap(a, j, j - 1);
                --j;
            }
        }
    }

    /**
     * Sort an array using "Selection sort" algorithm.
     *
     * Selection sort takes quadratic time to sort an array:
     * (N - 1) + (N - 2) + ... + 1 + 0 = 1/4N^2
     *
     * @param a array to be sorted
     */
    public static <T extends Comparable<T>> void selection(final T[] a) {
        final int n = a.length;

        for (int i = 0; i < n; ++i) {
            int min = i;

            for (int j = i + 1; j < n; ++j) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }

            swap(a, i, min);
        }
    }

    /**
     * http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     *
     * @param a array to be shuffled
     */
    public static <T extends Comparable<T>> void shuffle(final T[] a) {
        final Random generator = new Random();

        final int n = a.length;
        for (int i = n; i > 0; --i) {
            int rnd = generator.nextInt(i);
            swap(a, rnd, i - 1);
        }
    }


    private static <T extends Comparable<T>> boolean less(final T v, final T w) {
        return v.compareTo(w) < 0;
    }

    private static <T extends Comparable<T>> void swap(
            final T[] a,
            final int i,
            final int j) {

        final T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(final String[] args) {
        final Integer[] array = {7, 5, 3, 8, 4, 2, 9, 6};
        System.out.println("Initial array    = " + Arrays.toString(array));

        Sort.shell(array);
        System.out.println("Shell sorted     = " + Arrays.toString(array));

        Sort.shuffle(array);
        System.out.println("Shuffled         = " + Arrays.toString(array));

        Sort.insertion(array);
        System.out.println("Insertion sorted = " + Arrays.toString(array));

        Sort.shuffle(array);
        System.out.println("Shuffled         = " + Arrays.toString(array));
    }
}