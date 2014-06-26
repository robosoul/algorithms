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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public abstract class StackTest {
    private Stack<String> stack;

    @BeforeMethod
    public void setUp() throws Exception {
        stack = init();

        stack.push("...");
        stack.push("Luka");
        stack.push("<3");
        stack.push("Bazza");
    }

    protected abstract Stack<String> init();

    @Test
    public void testIsEmpty() throws Exception {
        assertFalse(stack.isEmpty());
        assertNotEquals(stack.size(), 0);
    }

    @Test
    public void testPeek() throws Exception {
        int size = stack.size();
        assertTrue(size > 0);

        final String top = stack.peek();
        assertEquals(top, "Bazza");

        // peek shouldn't remove top item from the stack
        assertEquals(size, stack.size());
    }

    @Test
    public void testPop() throws Exception {
        int size = stack.size();
        assertTrue(size > 0);

        String top = stack.pop();
        assertEquals(top, "Bazza");

        assertNotEquals(size, stack.size());

        top = stack.pop();
        assertEquals(top, "<3");

        assertEquals(size - 2, stack.size());
    }

    @Test
    public void testPush() throws Exception {
        final String item = "New top";

        int size = stack.size();

        stack.push(item);
        assertNotEquals(size, stack.size());
        assertEquals(size + 1, stack.size());

        final String top = stack.peek();
        assertEquals(top, item);
    }

    @Test
    public void testSize() throws Exception {
        int size = stack.size();

        stack.push("Agh?");
        assertNotEquals(size, stack.size());
        assertEquals(size + 1, stack.size());
    }
}