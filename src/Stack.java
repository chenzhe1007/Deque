/**
 * Created by Luke on 1/1/18.
 */
public class Stack {

    private Node first = null;

    private class Node {
        String item;
        Node next;

    }


    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop() {
        String cur = first.item;
        first = first.next;
        return cur;
    }


    public static void main(String[] args) {

    }



}

