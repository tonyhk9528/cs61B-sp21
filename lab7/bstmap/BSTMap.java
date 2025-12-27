package bstmap;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V>  {
    private int size;
    private Node root;


    private class Node {
        K k;
        V v;
        Node left;
        Node right;
        Node (K k, V v){
            this.k = k;
            this.v = v;
        }
    }

    BSTMap () {
        this.root = null;
        size = 1;
    }

    public void clear() {
        this.root = null;
    }

    public boolean containsKey(K k) {
        return this.containsKey(this.root, k);
    }

    private boolean containsKey(Node node, K k){
        if (node == null) {
            return false;
        }

        if (k.compareTo(node.k) > 0) {
            return containsKey(node.right, k);
        }

        if (k.compareTo(node.k) < 0) {
            return containsKey(node.left, k);
        }

        return true;
    }

    public V get(K k) {
        return get(this.root, k);

    }


    private V get(Node node, K k){
        if (node == null) {
            return null;
        }

        if (k.compareTo(node.k) > 0){
            return get(node.right, k);
        }

        if (k.compareTo(node.k) < 0) {
            return get(node.left, k);
        }

        return node.v;
    }

    public int size() {
        return this.size;
    }

    public void put(K k, V v) {
        put(k, v, this.root);
    }

    private Node put(K k, V v, Node node){

        if (node == null) {
            this.size += 1;
            return new Node(k, v);
        }

        if (k.compareTo(node.k) > 0) {
            node.right = put(k, v, node.right);
        }

        if (k.compareTo(node.k) < 0) {
            node.left = put(k, v, node.left);
        }


        return node;
    }


    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K k){
        throw new UnsupportedOperationException();
    }

    public V remove (K k, V v){
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        this.printInOrder(this.root);
    }

    private void printInOrder(Node node){
        if (node == null) {
            return;
        }

        printInOrder(node.left);

        System.out.println(node.k);

        printInOrder(node.right);
    }
}
