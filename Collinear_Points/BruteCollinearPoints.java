import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private int num = 0;
    private line first = new line(null, null);

    public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException();
        int i = points.length;
        for (int x = 0; x < i; x++) {
            if (points[x] == null)
                throw new IllegalArgumentException();}
        for (int x = 0; x < i; x++) {
            
            for (int y = x + 1; y < i; y++)
                if (points[x].compareTo(points[y]) == 0)
                    throw new IllegalArgumentException();

        }
        this.points = Arrays.copyOf(points, i);
        if(i<4)
            return;
        buildlist();
    }

    private class line {
        public LineSegment l;
        public line next;

        public line(LineSegment l, line next) {
            this.l = l;
            this.next = next;
        }
    }

    public int numberOfSegments() {
        return num;
    } // the number of line segments

    private void buildlist() {
        int i = points.length;
        Point[] p = new Point[4];
        for (int x = 0; x < i - 3; x++)
            for (int y = x + 1; y < i - 2; y++)
                for (int z = y + 1; z < i - 1; z++)
                    for (int w = z + 1; w < i; w++) {
                        if (points[x].slopeTo(points[y]) == points[x].slopeTo(points[z])
                                && points[x].slopeTo(points[z]) == points[x].slopeTo(points[w])) {
                            p[0] = points[x];
                            p[1] = points[y];
                            p[2] = points[z];
                            p[3] = points[w];
                            Arrays.sort(p);
                            LineSegment ls = new LineSegment(p[0], p[3]);
                            line add = new line(ls, first.next);
                            first.next = add;
                            num++;
                        }
                    }

    }

    public LineSegment[] segments() // the line segments
    {
        int i = 0;
        line curline = first.next;
        LineSegment[] ls = new LineSegment[num];
        while (curline != null) {
            ls[i] = curline.l;
            i++;
            curline = curline.next;
        }
        return ls;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.print(collinear.num);
    }

}