import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import queue.MyQueue;


public class MyQueueTest {
    @Test
    void testIsEmptyForEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    void testIsEmptyForNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    void testSizeOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void testSizeNotOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void testAddToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void testAddNullToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
    }

    @Test
    void testPeekFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void testPeekFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(null, queue.peek());

    }
}
