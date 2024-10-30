import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;


public class KdTree {
    private class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private boolean isVertical;
        private RectHV rect;

        public Node(Point2D p, Node left, Node right, boolean isVertical, RectHV rect) {
            this.p = p;
            this.left = left;
            this.right = right;
            this.isVertical = isVertical;
            this.rect = rect;
        }
    }

    private Node root;
    private int size;
    private Point2D nearest;
    private double minDistance;

    public KdTree() // construct an empty set of points
    {
        root = null;
        size = 0;
    }

    public boolean isEmpty() // is the set empty?
    {
        return root == null;
    }

    public int size() // number of points in the set
    {
        return size;
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        if (root == null) {
            root = new Node(p, null, null, true, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }
        Node cur = root;
        while (true) {
            if (cur.p.equals(p))
                return;
            if (cur.isVertical) {
                if (p.x() < cur.p.x()) {
                    if (cur.left == null) {
                        cur.left = new Node(p, null, null, false,
                                new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.p.x(), cur.rect.ymax()));
                        size++;
                        return;
                    } else
                        cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = new Node(p, null, null, false,
                                new RectHV(cur.p.x(), cur.rect.ymin(), cur.rect.xmax(), cur.rect.ymax()));
                        size++;
                        return;
                    } else
                        cur = cur.right;
                }
            } else {
                if (p.y() < cur.p.y()) {
                    if (cur.left == null) {
                        cur.left = new Node(p, null, null, true,
                                new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.rect.xmax(), cur.p.y()));
                        size++;
                        return;
                    } else
                        cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = new Node(p, null, null, true,
                                new RectHV(cur.rect.xmin(), cur.p.y(), cur.rect.xmax(), cur.rect.ymax()));
                        size++;
                        return;
                    } else
                        cur = cur.right;
                }
            }
        }
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        Node cur = root;
        while (cur != null) {
            if (cur.p.equals(p))
                return true;
            if (cur.isVertical) {
                if (p.x() < cur.p.x())
                    cur = cur.left;
                else
                    cur = cur.right;
            } else {
                if (p.y() < cur.p.y())
                    cur = cur.left;
                else
                    cur = cur.right;
            }
        }
        return false;
    }

    public void draw() // draw all points to standard draw
    {
        Node p = root;
        draw2(p);

    }

    private void draw2(Node p) {
        if (p != null && p.isVertical) {
            p.p.draw();
            StdDraw.line(p.p.x(), p.rect.ymin(), p.p.x(), p.rect.ymax());
            draw2(p.left);
            draw2(p.right);
        } else if (p != null && !p.isVertical) {
            p.p.draw();
            StdDraw.line(p.rect.xmin(), p.p.y(), p.rect.xmax(), p.p.y());
            draw2(p.left);
            draw2(p.right);
        }
    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        Node cur = root;
        LinkedList<Point2D> rangeSet = new LinkedList<Point2D>();
        range2(cur, rect, rangeSet);
        return rangeSet;
    }

    private void range2(Node p, RectHV rect, LinkedList<Point2D> rangeSet) {
        if (p != null) {
            if (rect.contains(p.p))
                rangeSet.add(p.p);
            if (p.left != null && rect.intersects(p.left.rect)) 
                range2(p.left, rect, rangeSet);
            if (p.right != null && rect.intersects(p.right.rect)) 
                range2(p.right, rect, rangeSet);
            }
        }
    

    public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        if (root == null)
            return null;
        Node cur = root;
        nearest = root.p;
        minDistance = p.distanceSquaredTo(root.p);
        nearest2(cur, p);
        return nearest;
    }

    private void nearest2(Node p, Point2D point) {
        if (p != null) {
            if (point.distanceSquaredTo(p.p) < minDistance){
                nearest = p.p;
                minDistance = point.distanceSquaredTo(p.p);
            }
            double d1,d2;
            if(p.right!=null)
            d1=p.right.rect.distanceSquaredTo(point);
            else
            d1=Double.POSITIVE_INFINITY;
            if(p.left!=null)
            d2=p.left.rect.distanceSquaredTo(point);
            else
            d2=Double.POSITIVE_INFINITY;
            if(d1<d2){
            if (p.right != null && d1 < minDistance){
                nearest2(p.right, point);}
            if (p.left != null && d2 < minDistance){

                nearest2(p.left, point);}
            }
            else{
            if (p.left != null && d2 < minDistance){
                nearest2(p.left, point);}
            if (p.right != null && d1 < minDistance){
                nearest2(p.right, point);}
            }
            
        }
    }

    public static void main(String[] args) // unit testing of the methods (optional)
    {

    }
}
