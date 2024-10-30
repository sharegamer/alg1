import edu.princeton.cs.algs4.In;

// The last six methods should throw an IllegalArgumentException if one (or both) of the input arguments are invalid teams.
public class BaseballElimination {
    private String[] teams;
    private int[][] against;
    private int[] wins;
    private int[] losses;
    private int[] remaining;
    private int n;

    public BaseballElimination(String filename) // create a baseball division from given filename in format specified
                                                // below
    {
        In in = new In(filename);
        int n = in.readInt();
        teams = new String[n];
        against = new int[n][n];
        wins = new int[n];
        losses = new int[n];
        remaining = new int[n];
        for (int i = 0; i < n; i++) {
            team[i] = in.readString();
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < n; j++) {
                against[i][j] = in.readInt();
            }
        }
    }

public              int numberOfTeams()                        // number of teams  
{

}

public Iterable<String> teams()                                // all teams

public              int wins(String team)                      // number of wins for given team

public              int losses(String team)                    // number of losses for given team

public              int remaining(String team)                 // number of remaining games for given team

public              int against(String team1, String team2)    // number of remaining games between team1 and team2

public          boolean isEliminated(String team)              // is given team eliminated?

public Iterable<String> certificateOfElimination(String team)  // subset R of teams that eliminates given team; null if not eliminated
}