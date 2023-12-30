import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private Point[] points;
    private int num = 0;
    private line first = new line(null, null);

    public FastCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException();
        int i = points.length;
        for (int x = 0; x < i; x++) {
            if (points[x] == null)
                throw new IllegalArgumentException();
        }
        for (int x = 0; x < i; x++) {

            for (int y = x + 1; y < i; y++)
                if (points[x].compareTo(points[y]) == 0)
                    throw new IllegalArgumentException();

        }
        this.points = Arrays.copyOf(points, i);
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
        for (int j = 0; j < i; j++) {
            int count = 0;
            double pre = 0;
            Point[] p = Arrays.copyOfRange(points, j, i);
            Point now = p[0];
            Comparator<Point> c = now.slopeOrder();
            Arrays.sort(p, c);
            int k = p.length;
            for (int x = 1; x < k; x++) {
                if (x == 1) {
                    count = 1;
                    pre = now.slopeTo(p[x]);
                } else if (now.slopeTo(p[x]) == pre) {
                    count++;
                    if (x == k - 1 && count >= 3) {
                        Point[] tmp = new Point[count + 1];
                        tmp[0] = p[0];
                        for (int w = 0; w < count; w++)
                            tmp[w + 1] = p[x - w];
                        Arrays.sort(tmp);
                        LineSegment l = new LineSegment(tmp[0], tmp[count]);
                        line newline = new line(l, first.next);
                        first.next = newline;
                        num++;
                    }

                } else {
                    if (count >= 3) {
                        Point[] tmp = new Point[count + 1];
                        tmp[0] = p[0];
                        for (int w = 1; w <= count; w++)
                            tmp[w] = p[x - w];
                        Arrays.sort(tmp);

                        LineSegment l = new LineSegment(tmp[0], tmp[count]);

                        line newline = new line(l, first.next);
                        first.next = newline;
                        num++;
                    }
                    count = 1;
                    pre = now.slopeTo(p[x]);
                }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.print(collinear.num);
    }
} // the line segments
