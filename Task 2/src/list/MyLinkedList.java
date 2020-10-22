package list;

import java.util.*;

public class MyLinkedList<E> implements List<E>{
    private Node<E> head, tail;
    private static int length = 0;

    public void print() {
        if (length == 0) {
            System.out.println("Empty list");
        } else {
            System.out.println("Size is: " + length);
            Node current = head;
            while (current != tail) {
                System.out.println(current.data);
                current = current.next;
            }
            System.out.println(tail.data);
        }
    }


    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        if (this.length == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Object object) {
        if(length == 0){
            throw new EmptyStackException();
        }
        Node current = head;
        while (current != null) {
            if (current.data.equals(object)) {
                return true;
            }
            current = current.next;
        }
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
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException("Null values are not permitted");
        }
        if (head == null) {
            head = new Node<>();
            head.data = data;
            Node current = head;
            tail = current;
            current.next = null;
            current.prev = null;
            ++length;
            return true;
        }
        Node<E> current = new Node<>();
        current.data = data;
        current.prev = tail;
        tail = tail.next = current;
        ++length;
        return true;
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
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public E get(int index) {
        int i = 0;
        checkIndex(index);
        Node current = head;
        while (current != null) {
            if (i == index)
                return (E) current.data;
            current = current.next;
            ++i;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E data) {
        int i = 0;
        checkIndex(index);
        if (data == null) {
            throw new NullPointerException("Null elements are not permitted");
        } else if ( head == null) {
            head = new Node<>();
            head.data = data;
            Node current = head;
            tail = current;
            current.next = null;
            current.prev = null;
            ++length;
            return;
        } else if (index == length) {
            add(data);
            return;
        } else if (index == 0) {
            Node<E> newNode = new Node<>();
            newNode.data = data;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            ++length;
            return;
        }
        Node current = head;
        Node prev = null;
        Node next = null;
        while (current != null) {

            if (index - 1 == i) {
                prev = current;
            } else if (index == i) {
                next = current;
            }
            ++i;
            current = current.next;
        }
        Node<E> newNode = new Node<>();
        newNode.data = data;
        prev.next = newNode;
        next.prev = newNode;
        newNode.prev = prev;
        newNode.next = next;
        ++length;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0 && length == 1) {
            E data = head.data;
            head = null;
            length = 0;
            return data;
        } else if (index == 0) {
            Node newNode = head;
            head = newNode.next;
            --length;
            return (E) newNode.data;
        } else if (index + 1 == length) {
            Node lastNode = tail.prev;
            Object object = lastNode.next.data;
            lastNode.next = null;
            tail = lastNode;
            --length;
            return (E) object;
        }
        Node current = head;
        int i = 0;
        while (current != null) {

            if (index == i) {
                break;
            }
            ++i;
            current = current.next;
        }
        Node prev = current.prev;
        Node next = current.next;
        prev.next = next;
        next.prev = prev;
        Object data = current.data;
        current = null;
        --length;
        return (E) data;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
    public void checkIndex(int index){
        if (index > this.size()) {
            throw new IndexOutOfBoundsException("Index is greater than list size");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index are not supported");
        }
    }
}
