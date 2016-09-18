import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        private Node next;
        private Node prev;
        private Item item;
    }

    private Node first, last;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size() {
        int size = 0;

        if (first != null) {
            size = size + 1;
            Node current = first;
            while (current.next != null) {
                current = current.next;
                size++;
            }
        }

        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newFirst = new Node();
        newFirst.next = first;
        newFirst.item = item;

        if (isEmpty()) {
            last = newFirst;
        } else {
            first.prev = newFirst;
        }

        first = newFirst;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newLast = new Node();
        newLast.item = item;
        newLast.prev = last;

        if (isEmpty()) {
            first = newLast;
        } else {
            last.next = newLast;
        }

        last = newLast;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new java.util.NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (last == null) {
            throw new java.util.NoSuchElementException();
        }

        Item item = last.item;
        last = last.prev;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing
    public static void main(String[] args) {
    }
}
