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
package org.hoshi.uf.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class NetworkTest {
    public static final Logger log = LoggerFactory.getLogger(NetworkTest.class);

    public static void main(String[] args) {
        final Network network = new Network(10);

        final Friend f1 = new Friend("Luka", "Obradovic");
        final Friend f2 = new Friend("Branka", "Obradovic");
        final Friend f3 = new Friend("Ivan", "Obradovic");
        final Friend f4 = new Friend("Slavica", "Obradovic");
        final Friend f5 = new Friend("Milica", "Obradovic");
        final Friend f6 = new Friend("Jovan", "Obradovic");

        network.add(f1);
        network.add(f2);
        network.add(f3);
        network.add(f4);
        network.add(f5);
        network.add(f6);

        System.out.printf("Are '%s' and '%s' connected? %b%n", f1, f2, network.areConnected(f1, f2));

        System.out.printf("Let's make them friends: '%s' --> '%s'%n", f1, f2);
        network.connect(f1, f2);

        System.out.printf("Now, are '%s' and '%s' connected? %b%n", f1, f2, network.areConnected(f1, f2));

        System.out.printf("Let's make '%s' and '%s' be friends%n", f5, f2);
        network.connect(f5, f2);
        System.out.printf("Are '%s' and '%s' connected? %b%n", f2, f5, network.areConnected(f2, f5));
        System.out.printf("But wait... are '%s' and '%s' connected (via '%s')? %b%n", f1, f5, f2, network.areConnected(f1, f5));


    }
}