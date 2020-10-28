
import list.MyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class MyLinkedListTest {
    @Test
    void testAddToTail() {
        MyLinkedList<Integer> listOfIntegers = new MyLinkedList<>();
        listOfIntegers.add(99);
        listOfIntegers.add(100);
        listOfIntegers.add(13);
        Assertions.assertEquals(listOfIntegers.get(0), 99);
        Assertions.assertEquals(3, listOfIntegers.size());
    }

    @Test
    void testAddToTailNull() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            listOfString.add(null);
        });
    }

    @Test
    void testContainsByValue() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        Assertions.assertEquals(true, listOfInteger.contains(1));
        Assertions.assertEquals(false, listOfInteger.contains(99));
    }

    @Test
    void testContainsByValueInEmptyList() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        Assertions.assertThrows(EmptyStackException.class, () -> {
            listOfInteger.contains(99);
        });
    }

    @Test
    void testSizeOfForEmptyList() {
        MyLinkedList<String> listOfStrings = new MyLinkedList<>();
        Assertions.assertEquals(0, listOfStrings.size());
    }

    @Test
    void testSizeOfForNotEmptyList() {
        MyLinkedList<String> listOfStrings = new MyLinkedList<>();
        listOfStrings.add("One");
        listOfStrings.add("Two");
        listOfStrings.add("Three");
        Assertions.assertEquals(3, listOfStrings.size());
    }

    @Test
    void testIsEmptyForEmptyList() {
        MyLinkedList<Integer> listOfIntegers = new MyLinkedList<>();
        Assertions.assertEquals(true, listOfIntegers.isEmpty());
    }

    @Test
    void testIsEmptyForNotEmptyList() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        listOfInteger.add(3);
        Assertions.assertEquals(false, listOfInteger.isEmpty());
    }

    @Test
    void testGetByIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("One");
        listOfString.add("Two");
        listOfString.add("Three");
        Assertions.assertEquals("Two", listOfString.get(1));
    }

    @Test
    void testGetByIndexWithTooLargeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.get(2);
        });
    }

    @Test
    void testGetByIndexWithNegativeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.get(-1);
        });
    }

    @Test
    void testAddByIndex() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(2, 4);
        Assertions.assertEquals(list.get(2), 4);
        Assertions.assertEquals(list.get(3), 3);
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void testAddByIndexWithNegativeIndex() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfInteger.add(-1, 1);
        });
    }

    @Test
    void testAddByIndexWithTooLargeIndex() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfInteger.add(3, 1);
        });
    }

    @Test
    void testAddByIndexAddNull() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            listOfInteger.add(0, null);
        });
    }

    @Test
    void testRemoveByIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("One");
        listOfString.add("Two");
        listOfString.add("Three");
        listOfString.add("Four");
        Assertions.assertEquals("One", listOfString.remove(0));
        Assertions.assertEquals(3, listOfString.size());
        Assertions.assertEquals("Three", listOfString.remove(1));
        Assertions.assertEquals(2, listOfString.size());
    }

    @Test
    void testRemoveByIndexWithTooLargeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.remove(3);
        });
    }

    @Test
    void testRemoveByIndexWithNegativeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.remove(-2);
        });
    }

    @Test
    void testRemoveByIndexFromOneElementList() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertEquals(listOfString.remove(0), "Zero");
        Assertions.assertEquals(true, listOfString.isEmpty());
    }


}
