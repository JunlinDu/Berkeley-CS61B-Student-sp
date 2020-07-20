import java.util.*;

import static org.junit.Assert.assertTrue;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Entry<K, V> {

        K key;

        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }

    }

    private final int INIT_CAPACITY;
    private final double LOAD_FACTOR;
    private int m;
    private int n;
    private ArrayList<Entry<K, V>>[] buckets;
    private HashSet<K> keySet;

    public MyHashMap () {
        this(16);
    }

    public MyHashMap(int init) {
        this(init, 0.75);
    }

    public MyHashMap(int init, double loadFactor) {
        this.INIT_CAPACITY = init;
        this.m = init;
        this.LOAD_FACTOR = loadFactor;
        this.n = 0;
        keySet = new HashSet<>();
        iniMap(init);
    }

    private void iniMap(int size) {
        buckets = new ArrayList[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private int hash(K key, int bucketSize) {
        return Math.floorMod(key.hashCode(), bucketSize);
    }

    @Override
    public void clear() {
        iniMap(INIT_CAPACITY);
        this.keySet = new HashSet<>();
        this.m = INIT_CAPACITY;
        this.n = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int index = hash(key, this.m);
            for (Entry<K, V> entry: this.buckets[index])
                if (entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    @Override
    public int size() {
        return this.n;
    }

    public void put(Entry<K, V> entry, ArrayList<Entry<K, V>>[] buckets) {
        buckets[hash(entry.key, buckets.length)].add(entry);
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            put(new Entry<K, V>(key, value), this.buckets);
            this.keySet.add(key);
            this.n++;
            if(this.n > m * LOAD_FACTOR) {
                this.buckets = resize(this.buckets);
                this.m *= 2;
            }
        } else {
            for (Entry<K, V> entry : this.buckets[hash(key, m)]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                }
            }
        }
    }

    private ArrayList<Entry<K, V>>[] resize(ArrayList<Entry<K, V>>[] buckets) {
        ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[m * 2];

        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = new ArrayList<>();
        }

        Iterator<K> itr = this.keySet.iterator();
        while (itr.hasNext()) {
            K key = itr.next();
            put(new Entry<>(key, get(key)), newBuckets);
        }

        return newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return this.keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet.iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
    }
}
