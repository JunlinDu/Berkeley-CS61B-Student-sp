import java.util.*;

import static org.junit.Assert.assertTrue;

public class MyHashMap<K, V> implements Map61B<K, V> {
    /* Alternative implementation: using inner class Entry, replace ULLMAP<K, V> with ArrayList<Entry>*/
    private final int INIT_CAPACITY;
    private final double LOAD_FACTOR;
    private int m;
    private int n;
    private ULLMap<K, V>[] buckets;
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
        buckets = new ULLMap[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ULLMap();
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
        return this.buckets[hash(key, m)].get(key);
    }

    @Override
    public int size() {
        return this.n;
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            this.buckets[hash(key, m)].put(key, value);
            this.n++;
            if(this.n > m * LOAD_FACTOR) {
                this.buckets = resize(this.buckets);
                this.m *= 2;
            }
        } else {
            this.buckets[hash(key, m)].put(key, value);
        }
    }

    private ULLMap<K, V>[] resize(ULLMap<K, V>[] buckets) {
        ULLMap<K, V>[] newBuckets = new ULLMap[m * 2];

        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = new ULLMap();
        }

        for (ULLMap<K, V> list : buckets) {
            Iterator<K> itr = list.iterator();
            while (itr.hasNext()) {
                K key = itr.next();
                ULLMap newList = newBuckets[hash(key, m * 2)];
                newList.put(key, list.get(key));
            }
        }

        return newBuckets;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (ULLMap<K, V> list: this.buckets) {
            Iterator<K> itr = list.iterator();
            while(itr.hasNext()) {
                set.add(itr.next());
            }
        }
        return set;
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
