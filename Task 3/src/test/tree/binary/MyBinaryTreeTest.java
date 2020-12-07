package tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class MyBinaryTreeTest {

// ADD
    @Test
    void testAddOneElementToEmptyBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");

        Assertions.assertNull(binaryTree.root.getLeft());
        Assertions.assertNull(binaryTree.root.getRight());
        Assertions.assertEquals(1, binaryTree.root.getKey());
    }

    @Test
    void testAddElementToRightOfBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");
        binaryTree.add(2, "String2");

        Assertions.assertNull(binaryTree.root.getLeft());
        Assertions.assertEquals(2, binaryTree.root.getRight().getKey());
        Assertions.assertEquals("String2", binaryTree.root.getRight().getVal());
        Assertions.assertEquals(1, binaryTree.root.getKey());
        Assertions.assertEquals("String1", binaryTree.root.getVal());
    }

    @Test
    void testAddElementToLeftOfBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");
        binaryTree.add(-1, "String2");

        Assertions.assertNull(binaryTree.root.getRight());
        Assertions.assertEquals(-1, binaryTree.root.getLeft().getKey());
        Assertions.assertEquals("String2", binaryTree.root.getLeft().getVal());
        Assertions.assertEquals(1, binaryTree.root.getKey());
        Assertions.assertEquals("String1", binaryTree.root.getVal());
    }

    @Test
    void testAddElementWithLeftRotation() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, 1);
        binaryTree.add(3, 3);
        binaryTree.add(5, 5);

        Assertions.assertEquals(1, binaryTree.root.getLeft().getKey());
        Assertions.assertEquals(3, binaryTree.root.getKey());
        Assertions.assertEquals(5, binaryTree.root.getRight().getKey());
        Assertions.assertEquals(2, binaryTree.root.getHeight());
    }

    @Test
    void testAddElementWithRightRotation() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(9, "Nine");
        binaryTree.add(4, "Four");
        binaryTree.add(2, "Two");

        Assertions.assertEquals(2, binaryTree.root.getHeight());
        Assertions.assertEquals(4, binaryTree.root.getKey());
        Assertions.assertEquals(2, binaryTree.root.getLeft().getKey());
        Assertions.assertEquals(9, binaryTree.root.getRight().getKey());


    }

    @Test
    void testAddElementWithMultipleRotation() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(3, "Three");
        binaryTree.add(2, "Two");
        binaryTree.add(10, "Ten");
        binaryTree.add(25, "TwentyFive");
        binaryTree.add(4, "Four");
        binaryTree.add(5, "Five");

        Assertions.assertEquals(4, binaryTree.root.getKey());
        Assertions.assertEquals(3, binaryTree.root.getLeft().getKey());
        Assertions.assertEquals(2, binaryTree.root.getLeft().getLeft().getKey());

        Assertions.assertEquals(10, binaryTree.root.getRight().getKey());
        Assertions.assertEquals(5, binaryTree.root.getRight().getLeft().getKey());
        Assertions.assertEquals(25, binaryTree.root.getRight().getRight().getKey());

        Assertions.assertEquals(3, binaryTree.root.getHeight());
    }

    @Test
    void testAddElementWithMultipleRotation2() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(12, "Twelve");
        binaryTree.add(15, "Fifteen");
        binaryTree.add(7, "Seven");
        binaryTree.add(3, "Three");
        binaryTree.add(5, "Five");
        binaryTree.add(6, "Six");

        Assertions.assertEquals(7, binaryTree.root.getKey());
        Assertions.assertEquals(5, binaryTree.root.getLeft().getKey());
        Assertions.assertEquals(3, binaryTree.root.getLeft().getLeft().getKey());

        Assertions.assertEquals(12, binaryTree.root.getRight().getKey());
        Assertions.assertEquals(6, binaryTree.root.getLeft().getRight().getKey());
        Assertions.assertEquals(15, binaryTree.root.getRight().getRight().getKey());

        Assertions.assertEquals(3, binaryTree.root.getHeight());
    }

    //Contains

    @Test
    void testContainsForExistingElement() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, "One");

        boolean contains = binaryTree.contains(1);

        Assertions.assertTrue(contains);
    }

    @Test
    void testContainsForNotExistingElement() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, "One");

        boolean contains = binaryTree.contains(2);

        Assertions.assertFalse(contains);
    }

//Get
    @Test
    void testGetExistingForElement() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, "One");

        String found = binaryTree.get(1);

        Assertions.assertEquals("One", found);
    }

    @Test
    void testGetForNotExistingElement() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, "One");
        Assertions.assertThrows(NoSuchElementException.class, ()-> {
            binaryTree.get(11);
        });
    }

    //Remove
    @Test
    void theOnlyElement() {
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, "One");

        binaryTree.remove(1);


        Assertions.assertNull(binaryTree.root);

    }

    @Test
    void fromRightSubtree() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, 1);
        binaryTree.add(4, 4);

        binaryTree.remove(4);

        Assertions.assertNull(binaryTree.root.getLeft());
        Assertions.assertNull(binaryTree.root.getRight());
        Assertions.assertEquals(1, binaryTree.root.getKey());
        Assertions.assertEquals(1, binaryTree.root.getHeight());
    }


}
