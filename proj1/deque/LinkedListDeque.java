package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    int size;
    Node sentinel;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    public void addFirst(T item) {
        this.size += 1;
        Node newNode = new Node(item, this.sentinel.next, this.sentinel);
        this.sentinel.next.prev = newNode;
        this.sentinel.next = newNode;
    }

    public void addLast(T item) {
        this.size += 1;
        Node newNode = new Node(item, this.sentinel, this.sentinel.prev);
        this.sentinel.prev.next = newNode;
        this.sentinel.prev = newNode;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node curr = this.sentinel.next;

        while (curr != this.sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        this.size -= 1;
        Node removeNode = this.sentinel.next;
        this.sentinel.next = removeNode.next;
        this.sentinel.next.prev = this.sentinel;

        return removeNode.item;
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }

        this.size -= 1;
        Node removeNode = this.sentinel.prev;
        this.sentinel.prev = removeNode.prev;
        this.sentinel.prev.next = this.sentinel;

        return removeNode.item;
    }

    public T get(int index) {
        int currIndex = 0;
        Node currNode = this.sentinel.next;

        while (currIndex != index) {
            currNode = currNode.next;
            currIndex += 1;
        }

        return currNode.item;
    }

    public T getRecursive(int index) {
        return getRecursive(index, this.sentinel.next);
    }

    private T getRecursive(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class LLDIterator implements Iterator<T> {
        int curr;

        LLDIterator() {
            this.curr = 0;
        }

        @Override
        public boolean hasNext() {
            return curr < size;
        }

        @Override
        public T next() {
            T currItem = LinkedListDeque.this.get(curr);
            this.curr += 1;
            return currItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<T> other = (Deque<T>) o;

        if (this.size != other.size()) {
            return false;
        }

        boolean flag = true;

        for (int i = 0; i < size; i++) {
            if (!(this.get(i).equals(other.get(i)))) {
                return false;
            }
        }

        return true;
    }



}
