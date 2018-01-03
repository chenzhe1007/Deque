import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ZChen on 1/2/2018.
 * Randomized queue implemented in array
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s = null;
    private int end, size, start;


    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        end = size = start = 0;
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
            s[--start] = item;
        } else {
            s[end++] = item;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(start, end);
        Item ans = s[index];
        if (index != start) {
            s[index] = s[start];
            s[start++] = null;
        } else {
            start++;
        }
        size--;
        if (!isEmpty() && size == s.length/4) {
            resize(s.length/2);
        } else if (isEmpty()) {
            end = 0;
            start = 0;
        }
        return ans;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(start, end);
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
            for (int i = start,j = 0; i < end; i++,j++) {
                items[j] = s[i];
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

        for (int i = start; i < end; i++,j++) {
            newS[j] = s[i];
        }
        s = newS;
        end = j;
        start = 0;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 15; i++) {
            if (i % 5 == 1) {
                StdOut.println("deque value: " + rq.dequeue());
            } else {
                rq.enqueue(String.valueOf(i));
            }
        }
        StdOut.print(rq.size() + "\n");
        for (String item : rq) {
            StdOut.print(item + ",");
        }
    }
}
