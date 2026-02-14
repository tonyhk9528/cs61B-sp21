package deque;

public class ArrayDeque<T> {
    int size;
    int nextFirst;
    int nextLast;
    T[] items;


    public ArrayDeque() {
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 0;
        this.items = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }

        int newFirst = (nextFirst + items.length) % items.length;
        items[newFirst] = item;
        this.nextFirst = (newFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        int newLast = (nextLast + items.length) % items.length;

        items[newLast] = item;
        this.nextLast = (newLast + 1 + items.length) % items.length;
        size += 1;
    }

    private void resize() {
        T[] newItem = (T[]) new Object[size * 2];

        if (nextFirst <= nextLast) {
            java.lang.System.arraycopy(items, nextFirst + 1, newItem, 0, size);
        } else {
            // first to items.length
            java.lang.System.arraycopy(items, nextFirst + 1, newItem, 0, items.length - nextFirst - 1);
            // 0 to last
            java.lang.System.arraycopy(items, 0, newItem, items.length - nextFirst - 1, nextLast);
        }

        nextFirst = items.length - 1;
        nextLast = size;
        this.items = newItem;
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

        if ((size - 1 / (double) (items.length)) < 0.25 && size > 16) {
            resize();
        }

        int newFirst = (nextFirst + items.length) % items.length;
        T removeItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst = newFirst - 1;
        size -= 1;

        return removeItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size - 1 / (double) items.length < 0.25 && size > 16) {
            resize();
        }

        int newLast = (nextLast + items.length) % items.length;
        T removeItem = items[newLast];
        items[newLast] = null;
        nextLast = newLast + 1;
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

}
