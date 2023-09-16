/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String champion = null;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            i++;
            if (StdRandom.bernoulli(1.0 / i))
                champion = s;
        }
        StdOut.println(champion);
    }
}
