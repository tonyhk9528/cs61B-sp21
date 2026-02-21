package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;
    int maxIndex;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
        this.maxIndex = 0;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(maxIndex);
    }

    @Override
    public void addFirst(T item) {
        if (this.isEmpty()) {
            super.addFirst(item);
            return;
        }

        T currMax = this.max();

        super.addFirst(item);

        if (this.comparator.compare(currMax, item) < 0) {
            this.maxIndex = 0;
        } else {
            this.maxIndex += 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (this.isEmpty()) {
            super.addLast(item);
            return;
        }

        T currMax = this.max();

        super.addLast(item);

        if (this.comparator.compare(currMax, item) < 0) {
            this.maxIndex = size - 1;
        }
    }

    @Override
    public T removeFirst() {
        T currMax = this.max();
        T removeItem = super.removeFirst();

        if (this.comparator.compare(currMax, removeItem) == 0) {
            this.maxIndex = findNewMax();
        } else {
            this.maxIndex -= 1;
        }
        return removeItem;
    }

    @Override
    public T removeLast() {
        T currMax = this.max();
        T removeItem = super.removeLast();

        if (this.comparator.compare(currMax, removeItem) == 0) {
            this.maxIndex = findNewMax();
        }
        return removeItem;
    }


    private int findNewMax() {
        if (this.isEmpty()) {
            return 0;
        }

        int index = 0;
        int maxInt = 0;
        T newMax = this.get(0);

        for (T item: this) {

            if (this.comparator.compare(item, newMax) > 0) {
                newMax = item;
                maxInt = index;
            }

            index++;

        }

        return maxInt;
    }
}
