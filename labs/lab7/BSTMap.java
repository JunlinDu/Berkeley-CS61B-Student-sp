import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K, V> implements Map61B<K, V> {

    private class Node<K extends Comparable<K>, V> {
        Node left;
        Node right;
        K key;
        V value;
        Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        size = 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key, Object value) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    public void printInOrder() {

    }

    public static void main(String[] args) {

    }

}
