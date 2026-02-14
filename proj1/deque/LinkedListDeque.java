package deque;

public class LinkedListDeque<T> {
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

    LinkedListDeque() {
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
        return getRecursive(index -1 , curr.next);
    }



}
