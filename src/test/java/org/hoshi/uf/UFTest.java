package org.hoshi.uf;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class UFTest {
    public static final int MAX_NUM_OF_ELEMENTS = 10;

    private static final Random RND_GEN = new Random();

    private UF uf;

    @BeforeMethod
    public void setUp() throws Exception {
        int what = RND_GEN.nextInt(3);

        if (what == 0) {
            uf = new QuickFindUF(MAX_NUM_OF_ELEMENTS);
        } else if (what == 1) {
            uf = new QuickUnionUF(MAX_NUM_OF_ELEMENTS);
        } else if (what == 2) {
            uf = new WeightedQuickUnionUF(MAX_NUM_OF_ELEMENTS);
        }

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
    public void testConnected() throws Exception {
        assertTrue(uf.connected(1, 2));
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(uf.count(), 3);
    }

}