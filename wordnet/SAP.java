// Corner cases.  Throw an IllegalArgumentException in the following situations:

// Any argument is null
// Any vertex argument is outside its prescribed range
// Any iterable argument contains a null item
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
public class SAP {

    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException("null argument");
        this.G = new Digraph(G);
    }

    private class Vtolength {
        private final int  v;
        private final int length;

        public Vtolength(int v, int length)
        {
            this.v = v;
            this.length = length;
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        if(v < 0 || v > G.V() - 1 || w < 0 || w > G.V() - 1 )
            throw new IllegalArgumentException("vertex argument is outside its prescribed range");
        Map<Integer, Integer> vMap = new HashMap<>();
        Map<Integer, Integer> wMap = new HashMap<>();
        Queue<Vtolength> vQueue = new LinkedList<>();
        Queue<Vtolength> wQueue = new LinkedList<>();
        Vtolength vnode = new Vtolength(v, 0);
        Vtolength wnode = new Vtolength(w, 0);
        int min = Integer.MAX_VALUE;
        vQueue.add(vnode);
        while(!vQueue.isEmpty())
        {
            Vtolength ver = vQueue.remove();
            int v1 = ver.v;
            if(vMap.containsKey(v1))
                if(vMap.get(v1) < ver.length)
                    continue; 
            vMap.put(ver.v, ver.length);
            for(int v2 : G.adj(v1))
            {
                    Vtolength vMap2 = new Vtolength(v2, ver.length + 1);
                    vQueue.add(vMap2);
            }
        }
        wQueue.add(wnode);
        while(!wQueue.isEmpty())
        {
            Vtolength ver = wQueue.remove();
            int w1 = ver.v;
            if (wMap.containsKey(w1))
                if(wMap.get(w1) < ver.length)
                    continue; 
            wMap.put(w1, ver.length);
            if(vMap.containsKey(w1))
                {
                    int length = vMap.get(w1) + ver.length;
                    if(length < min)
                        min = length;
                }
            for(int w2 : G.adj(w1))
            {
                    Vtolength wMap2 = new Vtolength(w2, ver.length + 1);
                    wQueue.add(wMap2);
            }
        }
        if(min == Integer.MAX_VALUE)
            return -1;
        else
            return min;


    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w)
    {
        if(v < 0 || v > G.V() - 1 || w < 0 || w > G.V() - 1 )
            throw new IllegalArgumentException("vertex argument is outside its prescribed range");
        Map<Integer, Integer> vMap = new HashMap<>();
        Map<Integer, Integer> wMap = new HashMap<>();
        Queue<Vtolength> vQueue = new LinkedList<>();
        Queue<Vtolength> wQueue = new LinkedList<>();
        Vtolength vnode = new Vtolength(v, 0);
        Vtolength wnode = new Vtolength(w, 0);
        int min = Integer.MAX_VALUE;
        int ancestor = -1;
        vQueue.add(vnode);
        while(!vQueue.isEmpty())
        {
            Vtolength ver = vQueue.remove();
            int v1 = ver.v;
            if(vMap.containsKey(v1))
                if(vMap.get(v1) < ver.length)
                    continue; 
            vMap.put(ver.v, ver.length);
            for(int v2 : G.adj(v1))
            {
                    Vtolength vMap2 = new Vtolength(v2, ver.length + 1);
                    vQueue.add(vMap2);
            }
        }
        wQueue.add(wnode);
        while(!wQueue.isEmpty())
        {
            Vtolength ver = wQueue.remove();
            int w1 = ver.v;
            if (wMap.containsKey(w1))
                if(wMap.get(w1) < ver.length)
                    continue; 
            wMap.put(w1, ver.length);
            if(vMap.containsKey(w1))
                {
                    int length = vMap.get(w1) + ver.length;
                    if(length < min)
                    {
                        min = length;
                        ancestor = w1;
                    }
                }
            for(int w2 : G.adj(w1))
            {
                    Vtolength wMap2 = new Vtolength(w2, ver.length + 1);
                    wQueue.add(wMap2);
            }
        }
        return ancestor;
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        if(v == null || w == null )
            throw new IllegalArgumentException("null argument");    
        for (Integer i : v)
            if (i == null || i < 0 || i >= G.V()) throw new IllegalArgumentException();
        for (Integer i : w)
            if (i == null || i < 0 || i >= G.V()) throw new IllegalArgumentException();
        int min = Integer.MAX_VALUE;
        for(int v1: v)
            for(int w1: w){
                int i=length(v1, w1);
                if(i < min && i != -1)
                    min = length(v1, w1);
            }
        if(min == Integer.MAX_VALUE)
            return -1;
        else
            return min;
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        if(v == null || w == null )
            throw new IllegalArgumentException("null argument");    
        for (Integer i : v)
            if (i == null || i < 0 || i >= G.V()) throw new IllegalArgumentException();
        for (Integer i : w)
            if (i == null || i < 0 || i >= G.V()) throw new IllegalArgumentException();
        int min = Integer.MAX_VALUE;
        int ancestor = -1;
        for(int v1: v)
            for(int w1: w){
                int i=length(v1, w1);
                if(i < min && i != -1)
                {
                    min = length(v1, w1);
                    ancestor = ancestor(v1, w1);
                }
            }
        return ancestor;
    }
 
    // do unit testing of this class
    public static void main(String[] args) {

        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

        }
    }
}
