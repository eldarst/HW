package tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class MyBinaryTreeTest {

// ADD
    @Test
    void testAddOneElementToEmptyBinaryTree(){
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(1, "String1");

        Assertions.assertNull(tree.root.getLeft());
        Assertions.assertNull(tree.root.getRight());
        Assertions.assertEquals(1, tree.root.getKey());
    }

    @Test
    void testAddElementToRightOfBinaryTree(){
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(1, "String1");
        tree.add(2, "String2");

        Assertions.assertNull(tree.root.getLeft());
        Assertions.assertEquals(2, tree.root.getRight().getKey());
        Assertions.assertEquals("String2", tree.root.getRight().getVal());
        Assertions.assertEquals(1, tree.root.getKey());
        Assertions.assertEquals("String1", tree.root.getVal());
    }

    @Test
    void testAddElementToLeftOfBinaryTree(){
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(1, "String1");
        tree.add(-1, "String2");

        Assertions.assertNull(tree.root.getRight());
        Assertions.assertEquals(-1, tree.root.getLeft().getKey());
        Assertions.assertEquals("String2", tree.root.getLeft().getVal());
        Assertions.assertEquals(1, tree.root.getKey());
        Assertions.assertEquals("String1", tree.root.getVal());
    }

    @Test
    void testAddElementWithLeftRotation() {
        MyBinarySearchTree<Integer, Integer> tree = new MyBinarySearchTree<>();

        tree.add(1, 1);
        tree.add(3, 3);
        tree.add(5, 5);

        Assertions.assertEquals(1, tree.root.getLeft().getKey());
        Assertions.assertEquals(3, tree.root.getKey());
        Assertions.assertEquals(5, tree.root.getRight().getKey());
        Assertions.assertEquals(2, tree.root.getHeight());
    }

    @Test
    void testAddElementWithRightRotation() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(9, "Nine");
        tree.add(4, "Four");
        tree.add(2, "Two");

        Assertions.assertEquals(2, tree.root.getHeight());
        Assertions.assertEquals(4, tree.root.getKey());
        Assertions.assertEquals(2, tree.root.getLeft().getKey());
        Assertions.assertEquals(9, tree.root.getRight().getKey());


    }

    @Test
    void testAddElementWithMultipleRotation() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(3, "Three");
        tree.add(2, "Two");
        tree.add(10, "Ten");
        tree.add(25, "TwentyFive");
        tree.add(4, "Four");
        tree.add(5, "Five");

        Assertions.assertEquals(4, tree.root.getKey());
        Assertions.assertEquals(3, tree.root.getLeft().getKey());
        Assertions.assertEquals(2, tree.root.getLeft().getLeft().getKey());

        Assertions.assertEquals(10, tree.root.getRight().getKey());
        Assertions.assertEquals(5, tree.root.getRight().getLeft().getKey());
        Assertions.assertEquals(25, tree.root.getRight().getRight().getKey());

        Assertions.assertEquals(3, tree.root.getHeight());
    }

    @Test
    void testAddElementWithMultipleRotation2() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.add(12, "Twelve");
        tree.add(15, "Fifteen");
        tree.add(7, "Seven");
        tree.add(3, "Three");
        tree.add(5, "Five");
        tree.add(6, "Six");

        Assertions.assertEquals(7, tree.root.getKey());
        Assertions.assertEquals(5, tree.root.getLeft().getKey());
        Assertions.assertEquals(3, tree.root.getLeft().getLeft().getKey());

        Assertions.assertEquals(12, tree.root.getRight().getKey());
        Assertions.assertEquals(6, tree.root.getLeft().getRight().getKey());
        Assertions.assertEquals(15, tree.root.getRight().getRight().getKey());

        Assertions.assertEquals(3, tree.root.getHeight());
    }

    //Contains

    @Test
    void testContainsForExistingElement() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();
        tree.add(1, "One");

        boolean contains = tree.contains(1);

        Assertions.assertTrue(contains);
    }

    @Test
    void testContainsForNotExistingElement() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();
        tree.add(1, "One");

        boolean contains = tree.contains(2);

        Assertions.assertFalse(contains);
    }

//Get
    @Test
    void testGetExistingForElement() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();
        tree.add(1, "One");

        String found = tree.get(1);

        Assertions.assertEquals("One", found);
    }

    @Test
    void testGetForNotExistingElement() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();
        tree.add(1, "One");
        Assertions.assertThrows(NoSuchElementException.class, ()-> {
            tree.get(11);
        });
    }

    //Remove
    @Test
    void testRemoveElementFromBinaryTree() {
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();
        tree.add(1, "One");

        tree.remove(1);


        Assertions.assertNull(tree.root);

    }

    @Test
    void testRemoveFromBinaryTree2() {
        MyBinarySearchTree<Integer, Integer> tree = new MyBinarySearchTree<>();
        tree.add(1, 1);
        tree.add(4, 4);

        tree.remove(4);

        Assertions.assertNull(tree.root.getLeft());
        Assertions.assertNull(tree.root.getRight());
        Assertions.assertEquals(1, tree.root.getKey());
        Assertions.assertEquals(1, tree.root.getHeight());
    }


}
