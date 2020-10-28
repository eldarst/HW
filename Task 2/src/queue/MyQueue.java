package queue;

import list.MyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyQueue<E> implements Queue<E> {
    private MyLinkedList<E> queue = new MyLinkedList();

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        if (queue.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean add(E e) {
        if (queue.add(e)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return queue.get(0);
        }
    }
}
