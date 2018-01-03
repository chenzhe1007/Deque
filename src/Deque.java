/**
 * Created by Luke on 1/1/18.
 * implement a deque data structure where data can be push/pop from both end
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first, last;
    private class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;
    }

    public Deque() {
        size = 0;
        first = last = null;
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
    public void addFirst(Item item) {
        isNull(item);

        if (isEmpty()) {
            Node<Item> newNode = new Node<Item>();
            newNode.item = item;
            first = last = newNode;
        } else {
            Node<Item> oldFirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        isNull(item);
        if (isEmpty()) {
            Node<Item> newNode = new Node<Item>();
            newNode.item = item;
            first = last = newNode;
        } else {
            Node<Item> oldLast = last;
            last = new Node<Item>();
            last.item = item;
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        size--;
        Item item = first.item;
        if (!isEmpty()) {
            first = first.next;
            first.prev = null;
        } else {
            last = first = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        size--;
        Item item = last.item;
        if (!isEmpty()) {
            last = last.prev;
            last.next = null;
        } else {
            first = last = null;
        }
        return item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = null;
        public ListIterator() {
            current = first;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                deque.addLast(String.valueOf(i));
            } else {
                deque.removeFirst();
                //deque.addLast(String.valueOf(i));
            }
        }
        //StdOut.print(deque.removeLast());

        for (String item : deque) {
            StdOut.print(item);
        }
    }
}

