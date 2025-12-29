package hashmap;

import edu.princeton.cs.algs4.SET;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    int n = 0; // number of items
    int m; // number of buckets
    double maxLoad; // n/m
    Set<K> keys = new HashSet<>();

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.buckets = createTable(initialSize);
        this.m = initialSize;
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = createBucket();
        }
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int getIndex(K k) {
        return Math.floorMod(k.hashCode(), this.m);
    }

    private int getIndex(K k, int mod) {
        return Math.floorMod(k.hashCode(), mod);
    }

    public void clear() {
        for (int i = 0; i < m; i++) {
            buckets[i] = createBucket();
        }
        n = 0;
        keys.clear();
    }


    public V remove(K k){
        throw new UnsupportedOperationException();
    }

    public V remove(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket){
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public V get(K key){
        int index = getIndex(key);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket){
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public int size(){
        return n;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        bucket.add(createNode(key, value));
        n += 1;
        if ((float) n/ (float) m > this.maxLoad) {
            resize();
        }
        keys.add(key);

    }

    private void resize() {
        int newM = 2 * m;
        Collection<Node>[] newBuckets = createTable(newM);
        for (int i = 0; i < newM; i++) {
            newBuckets[i] = createBucket();
        }

        for (int i = 0; i < this.m; i++) {
            Collection<Node >bucket = buckets[i];
            for (Node node : bucket) {
                int index = getIndex(node.key, newM);

                newBuckets[index].add(node);

            }
        }
        this.m = newM;
        this.buckets = newBuckets;
    }

    public Set<K> keySet() {
        return this.keys;
    }

    public Iterator iterator() {
        return keySet().iterator();
    }

}
