/**
 * Created by Luke on 1/1/18.
 */
public class StackResizingArray {

    private String[] s;
    private int N = 0;
    public StackResizingArray() {
        s = new String[1];
    }

    public boolean hasNext() {
        return N == 0;
    }


    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;

    }

    private void resize(int capacity) {
        String[] newS = new String[capacity];
        for (int i = 0; i < s.length; i++) {
            newS[i] = s[i];
        }
        s = newS;
    }

    public static void main(String[] args) {
        StackResizingArray stack = new StackResizingArray();

        stack.push("A");
        stack.push("B");

        System.out.println(stack.pop());

    }


}
