import org.junit.jupiter.api.Test;
import tree.binary.MyBinarySearchTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyBinaryTreeTest {

    @Test
    void testAddOneElementToEmptyBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");

        assertNull(binaryTree.root.getLeft());
        assertNull(binaryTree.root.getRight());
        assertEquals(1, binaryTree.root.getKey());
        binaryTree.print(binaryTree.root);
    }

    @Test
    void testAddElementToRightOfBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");
        binaryTree.add(2, "String2");

        assertNull(binaryTree.root.getLeft());
        assertEquals(2, binaryTree.root.getRight().getKey());
        assertEquals("String2", binaryTree.root.getRight().getVal());
        assertEquals(1, binaryTree.root.getKey());
        assertEquals("String1", binaryTree.root.getVal());
        binaryTree.print(binaryTree.root);
    }

    @Test
    void testAddElementToLeftOfBinaryTree(){
        MyBinarySearchTree<Integer, String> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, "String1");
        binaryTree.add(-1, "String2");

        assertNull(binaryTree.root.getRight());
        assertEquals(-1, binaryTree.root.getLeft().getKey());
        assertEquals("String2", binaryTree.root.getLeft().getVal());
        assertEquals(1, binaryTree.root.getKey());
        assertEquals("String1", binaryTree.root.getVal());
        binaryTree.print(binaryTree.root);
    }

    @Test
    void simpleLeftRotation() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(1, 1);
        binaryTree.add(3, 3);
        binaryTree.add(5, 5);

        assertEquals(1, binaryTree.root.getLeft().getKey());
        assertEquals(3, binaryTree.root.getKey());
        assertEquals(5, binaryTree.root.getRight().getKey());
        assertEquals(2, binaryTree.root.getHeight());
    }

    @Test
    void simpleRightRotation() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(7, 7);
        binaryTree.add(4, 4);
        binaryTree.add(2, 2);

        assertEquals(2, binaryTree.root.getHeight());
        assertEquals(4, binaryTree.root.getKey());
        assertEquals(2, binaryTree.root.getLeft().getKey());
        assertEquals(7, binaryTree.root.getRight().getKey());


    }

    @Test
    void doubleLeftRotation() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(2, 2);
        binaryTree.add(1, 1);
        binaryTree.add(7, 7);
        binaryTree.add(9, 9);
        binaryTree.add(4, 4);
        binaryTree.add(5, 5);

        assertEquals(4, binaryTree.root.getKey());
        assertEquals(2, binaryTree.root.getLeft().getKey());
        assertEquals(1, binaryTree.root.getLeft().getLeft().getKey());

        assertEquals(7, binaryTree.root.getRight().getKey());
        assertEquals(5, binaryTree.root.getRight().getLeft().getKey());
        assertEquals(9, binaryTree.root.getRight().getRight().getKey());

        assertEquals(3, binaryTree.root.getHeight());
    }

    @Test
    void doubleRightRotation() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();

        binaryTree.add(8, 8);
        binaryTree.add(9, 9);
        binaryTree.add(4, 4);
        binaryTree.add(3, 3);
        binaryTree.add(5, 5);
        binaryTree.add(6, 6);

        assertEquals(5, binaryTree.root.getKey());
        assertEquals(4, binaryTree.root.getLeft().getKey());
        assertEquals(3, binaryTree.root.getLeft().getLeft().getKey());

        assertEquals(8, binaryTree.root.getRight().getKey());
        assertEquals(6, binaryTree.root.getRight().getLeft().getKey());
        assertEquals(9, binaryTree.root.getRight().getRight().getKey());

        assertEquals(3, binaryTree.root.getHeight());
    }
    @Test
    void testAddNullToBinaryTree(){

    }

    @Test
    void testContainsInEmptyBinaryTree(){

    }

    @Test
    void testContains(){

    }

    @Test
    void testGetElement(){

    }

    @Test
    void testGetWithNotExistingElement(){

    }

    @Test
    void theOnlyElement() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, 1);

        binaryTree.remove(1);


        assertNull(binaryTree.root);

    }

    @Test
    void fromRightSubtree() {
        MyBinarySearchTree<Integer, Integer> binaryTree = new MyBinarySearchTree<>();
        binaryTree.add(1, 1);
        binaryTree.add(4, 4);

        binaryTree.remove(4);

        assertNull(binaryTree.root.getLeft());
        assertNull(binaryTree.root.getRight());
        assertEquals(1, binaryTree.root.getKey());
        assertEquals(1, binaryTree.root.getHeight());
    }

    @Test
    void testRemoveElement(){

    }

    @Test
    void testRemoveWithNotExistingElement(){
        
    }

}
