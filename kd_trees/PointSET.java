import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> set;

    public PointSET() // construct an empty set of points
    {
        set = new TreeSet<Point2D>();
    }

    public boolean isEmpty() // is the set empty?
    {
        return set.isEmpty();
    }

    public int size() // number of points in the set
    {
        return set.size();
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        set.add(p);
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        return set.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
        for (Point2D p : set)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        TreeSet<Point2D> rangeSet = new TreeSet<Point2D>();
        for (Point2D p : set)
            if (rect.contains(p))
                rangeSet.add(p);
        return rangeSet;
    }

    public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        if (set.isEmpty())
            return null;
        Point2D nearest = set.first();
        for (Point2D q : set)
            if (p.distanceSquaredTo(q) < p.distanceSquaredTo(nearest))
                nearest = q;
        return nearest;
    }

    public static void main(String[] args) // unit testing of the methods (optional)
    {
        PointSET set = new PointSET();
        set.insert(new Point2D(0.1, 0.1));
        set.insert(new Point2D(0.2, 0.2));
        set.insert(new Point2D(0.3, 0.3));
        set.insert(new Point2D(0.4, 0.4));
        set.insert(new Point2D(0.5, 0.5));
        set.insert(new Point2D(0.6, 0.6));
        set.insert(new Point2D(0.7, 0.7));
        set.insert(new Point2D(0.8, 0.8));
        set.insert(new Point2D(0.9, 0.9));
        set.insert(new Point2D(0.0, 0.0));
        set.insert(new Point2D(0.1, 0.2));
        set.insert(new Point2D(0.2, 0.3));
        set.insert(new Point2D(0.3, 0.4));
        set.insert(new Point2D(0.4, 0.5));
        set.insert(new Point2D(0.5, 0.6));
        set.insert(new Point2D(0.6, 0.7));
        set.insert(new Point2D(0.7, 0.8));
        set.insert(new Point2D(0.8, 0.9));
        set.insert(new Point2D(0.9, 0.0));
        set.insert(new Point2D(0.0, 0.1));
        set.insert(new Point2D(0.1, 0.3));
        set.insert(new Point2D(0.2, 0.4));
        set.insert(new Point2D(0.3, 0.5));
        set.insert(new Point2D(0.4, 0.6));
        set.insert(new Point2D(0.5, 0.7));
        set.insert(new Point2D(0.6, 0.8));
        set.insert(new Point2D(0.7, 0.9));
        set.insert(new Point2D(0.8, 0.0));
        set.insert(new Point2D(0.9, 0));
        set.draw();
    }
}