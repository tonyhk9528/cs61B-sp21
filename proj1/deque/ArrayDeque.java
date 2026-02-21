package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    int size;
    int nextFirst;
    int nextLast;
    T[] items;


    public ArrayDeque() {
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
        this.items = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        this.nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        this.nextLast = (nextLast + 1 + items.length) % items.length;
        size += 1;
    }

    private void resize(int capacity) {
        T[] newItem = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newItem[i] = get(i);
        }

        this.items = newItem;
        nextFirst = items.length - 1;
        nextLast = size;

    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < size + nextFirst + 1; i++) {
                System.out.print(items[i]);
            }
        } else {
            // first to items.length
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i]);
            }
            // 0 to last
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i]);
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (((size - 1) / (double) (items.length)) < 0.25 && items.length > 16) {
            resize(items.length / 2 ); // size down
        }

        int first = (nextFirst + 1 + items.length) % items.length;
        T removeItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;

        return removeItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if ((size - 1) / (double) items.length < 0.25 && items.length > 16) {
            resize(items.length / 2);
        }

        int last = (nextLast - 1 + items.length) % items.length;
        T removeItem = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;

        return removeItem;
    }

    public T get(int index) {
        // outbound check
        if (index < 0 || index >= size) {
            return null;
        }

        return items[(nextFirst + 1 + index) % items.length];
    }

    public Iterator<T> iterator() {
        return new arrayDequeIterator();
    }

    private class arrayDequeIterator implements Iterator<T> {
        int curr;

        private arrayDequeIterator() {
            this.curr = 0;
        }

        public boolean hasNext() {
            return curr < size;
        }

        public T next() {
            T currItem = ArrayDeque.this.get(curr);
            this.curr += 1;
            return currItem;
        }

    }

}
