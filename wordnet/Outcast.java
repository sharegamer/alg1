import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;
   public Outcast(WordNet wordnet)         // constructor takes a WordNet object
   {
         this.wordnet = wordnet;
   }
   public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
   {
            int max = 0;
            String outcast = null;
            for(String noun : nouns)
            {
                int distance = 0;
                for(String noun2 : nouns)
                {
                    distance += wordnet.distance(noun, noun2);
                }
                if(distance > max)
                {
                    max = distance;
                    outcast = noun;
                }
            }
            return outcast;
   }
   public static void main(String[] args)  // see test client below
   {
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
        In in = new In(args[t]);
        String[] nouns = in.readAllStrings();
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
   }

}
