/**
 * Created by Luke on 1/1/18.
 */
public class QueueOfStrings{

    private Node first;
    private Node last;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

    }

    public String dequeue() {
        String ans = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return ans;
    }
}
