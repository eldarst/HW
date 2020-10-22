import list.*;
import queue.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MyQueueTest {
    @Test
    void TestIsEmptyForEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    void TestIsEmptyForNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    void TestSizeOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void TestSizeNotOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddNullToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
    }

    @Test
    void TestPeekFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void TestPeekFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(null, queue.peek());

    }
}
