import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by ZChen on 1/3/2018.
 * testing client interface
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String cur = StdIn.readString();
            if (!cur.equals(" ")) {
                rq.enqueue(cur);
            }
        }
        while (k > 0) {
            StdOut.print(rq.dequeue() + "\n");
            k--;
        }
    }
}
