import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class Node {
        Node left, right;
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
        this.size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public Node getRecursive(K key, Node node) {
        if (node == null || key.equals(node.key)) {
            return node;
        }else if (key.compareTo(node.key) < 0) {
            return getRecursive(key, node.left);
        } else {
            return getRecursive(key, node.right);
        }
    }

    @Override
    public V get(K key) {
        Node node = getRecursive(key, this.root);
        if (node == null) return null;
        return node.value;
    }


    @Override
    public int size() {
        return this.size;
    }

    public Node putRecursive(K key, V value, Node node)
    {
        if(node == null) return new Node(key, value);

        if(key.compareTo(node.key) < 0) {
            node.left = putRecursive(key, value, node.left);
        } else {
            node.right = putRecursive(key, value, node.right);
        }
        return node;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            this.size++;
            return;
        }
        putRecursive(key, value, this.root);
        this.size++;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 4; i < 10; i++) {
            b.put("hi" + i, 1 + i);
        }
    }

}
