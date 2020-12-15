package com.eldar.tree.map;

import com.eldar.tree.binary.MyBinarySearchTree;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    MyBinarySearchTree<K, V> tree = new MyBinarySearchTree<>();
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return tree.contains((K) key);
    }

    @Override
    public V get(Object key) {
        return tree.get((K) key);
    }


    @Override
    public V put(K key, V value) {
        final V add = tree.add(key, value);
        if (add == null) {
            size = 0;
            return null;
        }
        size++;
        return add;
    }


    @Override
    public V remove(Object key) {
        tree.remove((K) key);
        size--;
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Unsupported method");
    }
}
