import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        RandomizedQueue rq = new RandomizedQueue<String>();
        if (i == 0)
            return;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        while (i != 0) {
            StdOut.println(rq.dequeue());
            i--;
        }
    }
}