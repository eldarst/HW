package queue;

import list.*;

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
        if(queue.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(queue.add(e)) {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
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
