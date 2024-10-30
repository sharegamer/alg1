import edu.princeton.cs.algs4.Queue;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[][] tiles;
    private int length;
    public Board(int[][] tiles){
        this.length = tiles.length;
        this.tiles = new int[length][length];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
                                           
    // string representation of this board
    public String toString()
    {
        String s = "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(length);
        for (int i = 0; i < tiles.length; i++) {
            sb.append("\n");
            for (int j = 0; j < tiles[i].length; j++) {
                sb.append(tiles[i][j]).append(" ");
            }

    }

        s = sb.toString();
        return s;
    }

    // board dimension n
    public int dimension(){
        return length;
    }

    // number of tiles out of place
    public int hamming(){
        int count = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                int goal = i * length + j + 1;
                if(tiles[i][j] != goal && tiles[i][j] != 0){
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int count = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                int goal = i * length + j + 1;
                if(tiles[i][j] != goal && tiles[i][j] != 0){
                    int row = (tiles[i][j] - 1) / length;
                    int col = (tiles[i][j] - 1) % length;
                    count += Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if(y == null){
            return false;
        }
        if(y.getClass() != this.getClass()){
            return false;
        }
        Board that = (Board) y;
        if(that.dimension() != this.dimension()){
            return false;
        }
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(that.tiles[i][j] != this.tiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors(){
        Queue<Board> q = new Queue<Board>();
        int row = 0;
        int col = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(tiles[i][j] == 0){
                    row = i;
                    col = j;
                }
            }
        }
        if(row > 0){
            int[][] copy = new int[length][length];
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    copy[i][j] = tiles[i][j];
                }
            }
            copy[row][col] = copy[row - 1][col];
            copy[row - 1][col] = 0;
            q.enqueue(new Board(copy));
        }
        if(row < length - 1){
            int[][] copy = new int[length][length];
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    copy[i][j] = tiles[i][j];
                }
            }
            copy[row][col] = copy[row + 1][col];
            copy[row + 1][col] = 0;
            q.enqueue(new Board(copy));
        }
        if(col > 0){
            int[][] copy = new int[length][length];
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    copy[i][j] = tiles[i][j];
                }
            }
            copy[row][col] = copy[row][col - 1];
            copy[row][col - 1] = 0;
            q.enqueue(new Board(copy));
        }
        if(col < length - 1){
            int[][] copy = new int[length][length];
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    copy[i][j] = tiles[i][j];
                }
            }
            copy[row][col] = copy[row][col + 1];
            copy[row][col + 1] = 0;
            q.enqueue(new Board(copy));
        }
        return q;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int[][] copy = new int[length][length];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                copy[i][j] = tiles[i][j];
            }
        }
        if(copy[0][0] != 0 && copy[0][1] != 0){
            int temp = copy[0][0];
            copy[0][0] = copy[0][1];
            copy[0][1] = temp;
        }
        else{
            int temp = copy[1][0];
            copy[1][0] = copy[1][1];
            copy[1][1] = temp;
        }
        return new Board(copy);
    }

    // unit testing (not graded)
    public static void main(String[] args){
        int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b = new Board(tiles);
        System.out.println(b);
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
        System.out.println(b.isGoal());
        System.out.println(b.twin());
        for(Board board : b.neighbors()){
            System.out.println(board);
        }
    }

}
