
import list.MyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

public class MyLinkedListTest {
    @Test
    void TestAddToTail() {
        MyLinkedList<Integer> listOfIntegers = new MyLinkedList<>();
        listOfIntegers.add(99);
        listOfIntegers.add(100);
        listOfIntegers.add(13);
        Assertions.assertEquals(listOfIntegers.get(0), 99);
        Assertions.assertEquals(3, listOfIntegers.size());
    }

    @Test
    void TestAddToTailNull() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            listOfString.add(null);
        });
    }

    @Test
    void TestContainsByValue() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        Assertions.assertEquals(true, listOfInteger.contains(1));
        Assertions.assertEquals(false, listOfInteger.contains(99));
    }

    @Test
    void TestContainsByValueInEmptyList() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        Assertions.assertThrows( EmptyStackException.class , () -> {
            listOfInteger.contains(99);
        });
    }
    @Test
    void TestSizeOfForEmptyList() {
        MyLinkedList<String> listOfStrings = new MyLinkedList<>();
        Assertions.assertEquals(0, listOfStrings.size());
    }

    @Test
    void TestSizeOfForNotEmptyList() {
        MyLinkedList<String> listOfStrings = new MyLinkedList<>();
        listOfStrings.add("One");
        listOfStrings.add("Two");
        listOfStrings.add("Three");
        Assertions.assertEquals(3, listOfStrings.size());
    }

    @Test
    void TestIsEmptyForEmptyList() {
        MyLinkedList<Integer> listOfIntegers = new MyLinkedList<>();
        Assertions.assertEquals(true, listOfIntegers.isEmpty());
    }

    @Test
    void TestIsEmptyForNotEmptyList() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        listOfInteger.add(3);
        Assertions.assertEquals(false, listOfInteger.isEmpty());
    }

    @Test
    void TestGetByIndex() {
        MyLinkedList<String> listOfString= new MyLinkedList<>();
        listOfString.add("One");
        listOfString.add("Two");
        listOfString.add("Three");
        Assertions.assertEquals("Two", listOfString.get(1));
    }

    @Test
    void TestGetByIndexWithTooLargeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.get(2);
        });
    }

    @Test
    void TestGetByIndexWithNegativeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.get(-1);
        });
    }

    @Test
    void TestAddByIndex() {
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
    void TestAddByIndexWithNegativeIndex() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfInteger.add(-1, 1);
        });
    }

    @Test
    void TestAddByIndexWithTooLargeIndex() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        listOfInteger.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfInteger.add(3, 1);
        });
    }

    @Test
    void TestAddByIndexAddNull() {
        MyLinkedList<Integer> listOfInteger = new MyLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            listOfInteger.add(0, null);
        });
    }

    @Test
    void TestRemoveByIndex() {
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
    void TestRemoveByIndexWithTooLargeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.remove(3);
        });
    }

    @Test
    void TestRemoveByIndexWithNegativeIndex() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfString.remove(-2);
        });
    }

    @Test
    void TestRemoveByIndexFromOneElementList() {
        MyLinkedList<String> listOfString = new MyLinkedList<>();
        listOfString.add("Zero");
        Assertions.assertEquals(listOfString.remove(0), "Zero");
        Assertions.assertEquals(true, listOfString.isEmpty());
    }



}
