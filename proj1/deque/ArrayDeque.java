package deque;

public class ArrayDeque<T> {
    int size;
    int first;
    int last;
    T[] items;


    public ArrayDeque() {
        this.size = 0;
        this.first = 0;
        this.last = 0;
        this.items = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }

        int newFirst = (first - 1 + items.length) % items.length;
        items[newFirst] = item;
        this.first = newFirst;
        size += 1;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        int newLast = (last + 1 + items.length) % items.length;

        items[newLast] = item;
        this.last = newLast;
        size += 1;
    }

    private void resize() {
        T[] newItem = (T[]) new Object[size * 2];

        if (first <= last) {
            java.lang.System.arraycopy(items, first, newItem, 0, size);
        } else {
            // first to items.length
            java.lang.System.arraycopy(items, first, newItem, 0, items.length - first);
            // 0 to last
            if (first != 0) {
                java.lang.System.arraycopy(items, 0, newItem, items.length - first, last);
            }
        }

        first = 0;
        last = size;
        this.items = newItem;
    }

    public boolean isEmpmty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (first <= last) {
            for (int i = first; i < size -first; i++) {
                System.out.print(items[i]);
            }
        } else {
            // first to items.length
            for (int i = first; i < items.length; i++) {
                System.out.print(items[i]);
            }
            // 0 to last
            for (int i = 0; i < last + 1; i++) {
                System.out.print(items[i]);
            }
        }
    }

    public T removeFirst() {
        if (isEmpmty()) {
            return null;
        }

        if ((size - 1 / (double) (items.length)) < 0.25 && size > 16) {
            resize();
        }

        int newFirst = (first + 1 + items.length) % items.length;
        T removeItem = items[first];
        items[first] = null;
        first = newFirst;
        size -= 1;

        return removeItem;
    }

    public T removeLast() {
        if (isEmpmty()) {
            return null;
        }

        if (size - 1 / (double) items.length < 0.25 && size > 16) {
            resize();
        }

        int newLast = (last - 1 + items.length) % items.length;
        T removeItem = items[last];
        items[last] = null;
        last = newLast;
        size -= 1;

        return removeItem;
    }

    public T get(int index) {
        // outbound check
        if (index < 0 || index >= size) {
            return null;
        }

        return items[(first + index) % items.length];
    }

}
