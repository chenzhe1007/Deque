/**
 * Created by ZChen on 1/2/2018.
 * Array(resizing) implementation of queue
 */
public class QueueArray<Item> {

    private Item[] s;
    private int start, end;
    private int size;

    public QueueArray() {
        s = (Item[]) new Object[1];
        start = end = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        size++;
        if (size == s.length) {
            resize(2 * s.length);
        } else if (end == s.length && start != 0) {
            end = 0;
        }
        //System.out.println(start + " : " + end + " : " + s.length);
        s[end++] = item;
    }

    public Item dequeue() {
        Item item = s[start];
        s[start] = null;
        if (start == s.length - 1) {
            start = 0;
        } else {
            start++;
        }
        size--;
        if (size == s.length/4) {
            resize(s.length/2);
        }
        return item;
    }

    public void resize(int capacity) {
        Item[] newS = (Item[]) new Object[capacity];
        int start = this.start;
        int end = this.end;
        int i = 0;
        while (start != end) {
            if (start == s.length) {
                start = 0;
            }
            newS[i++] = s[start++];
        }
        s = newS;
        this.start = 0;
        this.end = i;

    }

    public static void main(String[] args) {
        QueueArray<String> queue = new QueueArray<String>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue.dequeue());
        queue.enqueue("D");
        System.out.println(queue.dequeue());
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());




        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }

}
