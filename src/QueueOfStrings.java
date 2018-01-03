import edu.princeton.cs.algs4.StdRandom;

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

    public static void main(String[] args){
        int[] a = StdRandom.permutation(10);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
