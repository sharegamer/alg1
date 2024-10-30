import edu.princeton.cs.algs4.MinPQ;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
public class Solver {



    
    private boolean solvable;
    private SearchNode solution;

    private class SearchNode implements Comparable<SearchNode>{
    private Board board;
    private int moves;
    private SearchNode prev;
    private int priority;
    public SearchNode(Board board){
        this.board = board;
        this.moves = 0;
        this.prev = null;
        this.priority = board.manhattan() + moves;
    }
    public SearchNode(Board board, SearchNode prev){
        this.board = board;
        this.moves = prev.moves+1;
        this.prev = prev;
        this.priority = board.manhattan() + this.moves;
    }
    public int compareTo(SearchNode that){
        return this.priority - that.priority;
    }
}

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        MinPQ<SearchNode> pq;
        MinPQ<SearchNode> pqTwin;   
        if(initial == null){
            throw new IllegalArgumentException();
        }
        Board twin = initial.twin();
        pq = new MinPQ<SearchNode>();
        pqTwin = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(initial));
        pqTwin.insert(new SearchNode(twin));
        while(true){
            SearchNode min = pq.delMin();
            SearchNode minTwin = pqTwin.delMin();
            if(min.board.isGoal()){
                solvable = true;
                solution = min;
                break;
            }
            if(minTwin.board.isGoal()){
                solvable = false;
                break;
            }
            for(Board b : min.board.neighbors()){
                if(min.prev == null || !b.equals(min.prev.board)){
                    pq.insert(new SearchNode(b, min));
                }
            }
            for(Board b : minTwin.board.neighbors()){
                if(minTwin.prev == null || !b.equals(minTwin.prev.board)){
                    pqTwin.insert(new SearchNode(b, minTwin));
                }
            }
        }
        
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable()
    {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        if(!isSolvable()){
            return -1;
        }
        else{
            return solution.moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if(!isSolvable()){
            return null;
        }
        else{
            Stack<Board> s = new Stack<Board>();
            SearchNode current = solution;
            while(current != null){
                s.push(current.board);
                current = current.prev;
            }
            return s;
        }
    }

    // test client (see below) 
    public static void main(String[] args)
    {
        // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
    }

}