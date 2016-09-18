import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = n;
        private int[] indexes;

        public RandomizedQueueIterator() {
            indexes = new int[n];

            for (int j = 0; j < n; j++) {
                indexes[j] = j;
            }

            StdRandom.shuffle(indexes);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            int index = indexes[--i];
            return q[index];
        }
    }

    private Item[] q;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (n == q.length) {
            resize(2 * q.length);
        }
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int r = StdRandom.uniform(n);
        Item item = q[r];

        q[r] = q[--n];
        q[n] = null;

        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        return q[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing
    public static void main(String[] args) {
    }
}
