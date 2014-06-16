package org.hoshi.uf;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class QuickUnionUFTest {
    public static final int MAX_NUM_OF_ELEMENTS = 10;

    private UF uf;

    @BeforeMethod
    public void setUp() throws Exception {
        uf = new QuickUnionUF(MAX_NUM_OF_ELEMENTS);

        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(3, 4);
        uf.union(5, 6);
        uf.union(7, 8);
        uf.union(7, 9);
        uf.union(2, 8);
        uf.union(0, 5);
        uf.union(1, 9);
    }

    @Test
    public void testFind() throws Exception {
        assertEquals(uf.find(1), 9);
    }

    @Test
    public void testUnion() throws Exception {

    }

    @Test
    public void testConnected() throws Exception {
        assertTrue(uf.connected(1, 2));
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(uf.count(), 3);
    }
}