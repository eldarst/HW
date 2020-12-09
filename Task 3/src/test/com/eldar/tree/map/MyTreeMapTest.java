package com.eldar.tree.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyTreeMapTest {

    @Test
    void testPutToEmptyMyTreeMap() {
        MyTreeMap<Integer, Integer> tree = new MyTreeMap<>();
        tree.put(1, 2);
        Assertions.assertTrue(tree.containsKey(1));
    }

    @Test
    void testPutToNotEmptyMyTreeMap() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertTrue(tree.containsKey(12));
    }

    @Test
    void testGetElem() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertEquals(2, tree.get(12));
    }

    @Test
    void testContainsElem() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertTrue(tree.containsKey(12));
    }

    @Test
    void testIsEmptyWithEmptyMap() {
        MyTreeMap tree = new MyTreeMap<>();
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testIsEmptyWithNotEmptyMap() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(12, 2);
        Assertions.assertEquals(1, tree.size());
        Assertions.assertFalse(tree.isEmpty());
    }

}