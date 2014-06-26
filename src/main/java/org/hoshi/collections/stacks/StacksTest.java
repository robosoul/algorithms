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
public class StacksTest {
    public static void main(final String[] args) {
        final Stack<String> stackOfStrings = new ArrayStack<String>();

        stackOfStrings.push("...");
        stackOfStrings.push("Luka");
        stackOfStrings.push("<3");
        stackOfStrings.push("Bazza");

        System.out.println(stackOfStrings.toString());

        assert !stackOfStrings.isEmpty();
        assert stackOfStrings.size() == 4;
        assert stackOfStrings.pop().equals("Bazza");
        assert stackOfStrings.size() == 3;
        stackOfStrings.pop();
        assert stackOfStrings.size() == 2;
        assert stackOfStrings.pop().equals("Luka");
        assert stackOfStrings.size() == 1;
        stackOfStrings.pop();
    }
}