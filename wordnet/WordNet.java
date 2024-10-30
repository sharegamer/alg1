
// Corner cases.  Throw an IllegalArgumentException in the following situations:
// Any argument to the constructor or an instance method is null
// The input to the constructor does not correspond to a rooted DAG.
// Any of the noun arguments in distance() or sap() is not a WordNet noun.
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {

    private final Map<String, Set<Integer>> nounMap = new HashMap<String, Set<Integer>>();
    
    private final SAP sap;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        Digraph G;
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("null argument");

        In in = new In(synsets);
        String line = in.readLine();
        int linenum=0;
        while (line != null) {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
            String[] nouns = fields[1].split(" ");
            for (String noun : nouns) {
                if (nounMap.containsKey(noun))
                    nounMap.get(noun).add(id);
                else {
                    Set<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    nounMap.put(noun, ids);
                }
            }
            line = in.readLine();
            linenum++;
        }
        
        In in2 = new In(hypernyms);
        G = new Digraph(linenum);
        line = in2.readLine();
        while (line != null) {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                int hypernym = Integer.parseInt(fields[i]);
                G.addEdge(id, hypernym);
            }
            line = in2.readLine();
        }
        int rootNumber = 0;
        for(int i = 0; i < G.V(); i++){
            if(G.outdegree(i) == 0){
                rootNumber++;
            }
        }
        // check if rooted
        if(rootNumber != 1){
            throw new IllegalArgumentException(rootNumber+"");
        }
        
        Topological t = new Topological(G);
        if (!t.hasOrder()) throw new IllegalArgumentException();
        sap = new SAP(G);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("null argument");
        return nounMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("null argument");
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("not a WordNet noun");
        Set<Integer> idsA = nounMap.get(nounA);
        Set<Integer> idsB = nounMap.get(nounB);
        return sap.length(idsA, idsB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA
    // and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException("null argument");
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("not a WordNet noun");
        Set<Integer> idsA = nounMap.get(nounA);
        Set<Integer> idsB = nounMap.get(nounB);
        int ancestor = sap.ancestor(idsA, idsB);
        String result = getNouns(ancestor);
        
        return result.substring(0, result.length() - 1);
    }
    private String getNouns(int id) {
        StringBuilder sb = new StringBuilder();
        for (String noun : nounMap.keySet()) {
            if (nounMap.get(noun).contains(id))
                sb.append(noun + " ");
        }
        return sb.toString();
    }

    // do unit testing of this class
    public static void main(String[] args) {
        return;
    }
}