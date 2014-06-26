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

import org.hoshi.collections.stacks.ArrayStack;
import org.hoshi.collections.stacks.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class Tail {
    public static final Logger log = LoggerFactory.getLogger(Tail.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing argument: n.");
            System.exit(1);
        }

        final int n = Integer.parseInt(args[0]);


        final Stack<String> lines = new ArrayStack<String>();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = in.readLine()) != null) {
                lines.push(line);
            }

            for (int i = 0; i < n; ++i) {
                System.out.println(lines.pop());
            }
        } catch (IOException ioex) {
            System.err.println("Failed reading input: " + ioex.toString());
            System.exit(1);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Failed closing input: " + e.toString());
                    System.exit(1);
                }
            }
        }
    }
}