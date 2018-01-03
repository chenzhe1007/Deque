import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ZChen on 1/2/2018.
 * Randomized queue implemented in array
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s = null;
    private int end, size;
    private QueueOfStrings empSpace;
    private class QueueOfStrings {

        private Node first, last;
        private class Node {
            int item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int dequeue() {
            int item = first.item;
            first = first.next;
            if (isEmpty()) {
                last = null;
            }
            return item;
        }

        public void enqueue(int item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()) {
                first = last;
            } else {
                oldLast.next = last;
            }
        }
    }

    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        end = size = 0;
        empSpace = new QueueOfStrings();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void isNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    public void enqueue(Item item) {
        isNull(item);
        size++;
        if (size == s.length) {
            resize(2*s.length);
            s[end++] = item;
        } else if (end == s.length) {
            s[empSpace.dequeue()] = item;
        } else {
            s[end++] = item;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(end);
        while (s[index] == null) {
            index = StdRandom.uniform(end);
        }
        Item ans = s[index];
        s[index] = null;
        size--;
        if (!isEmpty() && size == s.length/4) {
            resize(s.length/2);
        } else if (isEmpty()) {
            end = 0;
        } else {
            empSpace.enqueue(index);
        }
        return ans;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(end);
        while (s[index] == null) {
            index = StdRandom.uniform(end);
        }
        return s[index];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Item[] items;
        private int current;
        public ListIterator() {
            items = (Item[]) new Object[size()];
            for (int i = 0, j = 0; i < s.length && i < end; i++) {
                if (s[i] == null) {
                    continue;
                }
                items[j++] = s[i];
            }
            StdRandom.shuffle(items);
            current = 0;
        }
        public boolean hasNext() {
            return current < items.length;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++];
        }

    }

    private void resize(int capacity) {
        Item[] newS = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < s.length && i < end; i++) {
            if (s[i] == null) {
                continue;
            }
            newS[j++] = s[i];
        }
        s = newS;
        end = j;
        empSpace = new QueueOfStrings();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                rq.enqueue(String.valueOf(i));
            } else {
                rq.dequeue();
            }
            rq.enqueue(String.valueOf(i));
        }
    }
}
