package bearmaps;

import java.util.Comparator;
import java.util.List;


public class KDTree {

    private static class Node {

        Point point;

        boolean isVertical;

        Node rightUp;

        Node leftDown;

        Node(Point point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
        }

        public void setVertical(boolean vertical) {
            this.isVertical = vertical;
        }

        public boolean isVertical() {
            return this.isVertical;
        }
    }

    private Node tree;

    public KDTree(List<Point> points) {
        constructTree(points);
    }

    public int compare(Node o1, Node o2) {
        int comp = o1.isVertical ? Double.compare(o1.point.getX(), o2.point.getX()) :
                Double.compare(o1.point.getY(), o2.point.getY());
        return comp;
    }

    private void constructTree(List<Point> points) {
        for (Point point : points) {
            if (tree == null) this.tree = new Node(point, true);
            insert(new Node(point, false), this.tree, tree.isVertical);
        }
    }

    private Node insert(Node newNode, Node tree, boolean isVertical) {
        if (tree == null) {
            newNode.setVertical(!isVertical);
            return newNode;
        }

        if (newNode.point.equals(tree.point)) return tree;

        if (compare(tree, newNode) > 0)
            tree.leftDown = insert(newNode, tree.leftDown, tree.isVertical);
        else tree.rightUp =insert(newNode, tree.rightUp, tree.isVertical);

        return tree;
    }

    public Point nearest(double x, double y) {
        return nearestRecursive(this.tree, new Point(x, y), this.tree).point;
    }

    public Node nearestRecursive(Node node, Point goal, Node best) {
        if (node == null) return best;

        if (Point.distance(node.point, goal) < Point.distance(best.point, goal))
            best = node;

        Node goodSide, badSide;
        if (compare(node, new Node(goal, true)) > 0) {
            goodSide = node.leftDown;
            badSide = node.rightUp;
        } else {
            goodSide = node.rightUp;
            badSide = node.leftDown;
        }

        best = nearestRecursive(goodSide, goal, best);

        if (isUseful(node, goal, best))
            best = nearestRecursive(badSide, goal, best);

        return best;
    }

    private boolean isUseful(Node node, Point goal, Node best) {
        double bestDis = Point.distance(best.point, goal);
        if (node.isVertical) return bestDis > Point.distance (new Point(goal.getX(), node.point.getY()), goal);
        else return bestDis > Point.distance(new Point(node.point.getX(), goal.getY()), goal);
    }

    public static void main(String[] args) {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p2_5 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 5.0);
        Point p4 = new Point(3.0, 3.0);
        Point p5 = new Point(4.0, 4.0);
        Point p6 = new Point(1.0, 5.0);
        KDTree kdTree = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point nearestPoint = kdTree.nearest(1.0, 7.0);
        System.out.println(nearestPoint.getX()); // evaluates to 1.0
        System.out.println(nearestPoint.getY()); // evaluates to 5.0
        nearestPoint = kdTree.nearest(4.0, 3.0);
        System.out.println(nearestPoint.getX()); // evaluates to 4.0
        System.out.println(nearestPoint.getY()); // evaluates to 2.0
        nearestPoint = kdTree.nearest(5.0, 4.0);
        System.out.println(nearestPoint.getX()); // evaluates to 4.0
        System.out.println(nearestPoint.getY()); // evaluates to 4.0
        System.out.println();

//        Point nearestPoint = kdTree.nearest(1.0, 7.0);
//        System.out.println(nearestPoint.getX()); //1
//        System.out.println(nearestPoint.getY()); //5

//        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
//        Point p2 = new Point(3.3, 4.4);
//        Point p3 = new Point(-2.9, 4.2);
//        KDTree.Node n1 = new KDTree.Node(p1, false);
//        KDTree.Node n2 = new KDTree.Node(p2, true);
////        KDTree kdTree = new KDTree(List.of(p1, p2, p3));
//        Point nearestPoint = kdTree.nearest(3.0, 4.0);
//        System.out.println(nearestPoint.getX()); // evaluates to 3.3
//        System.out.println(nearestPoint.getY()); // evaluates to 4.4


    }

}
