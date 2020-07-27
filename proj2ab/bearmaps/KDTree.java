package bearmaps;

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

    }

    private Node tree;

    public KDTree(List<Point> points) {
        constructTree(points);
    }

    public int compare(Node o1, Node o2) {
        return o1.isVertical ? Double.compare(o1.point.getX(), o2.point.getX()) :
                Double.compare(o1.point.getY(), o2.point.getY());
    }

    private void constructTree(List<Point> points) {
        for (Point point : points) {
            if (tree == null) this.tree = new Node(point, true);
            insert(new Node(point, false), this.tree, tree.isVertical);
        }
    }

    private Node insert(Node newNode, Node tree, boolean isVertical) {
        if (tree == null) {
            newNode.isVertical = !isVertical;
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
        if (node.isVertical)
            return bestDis > Point.distance (new Point(goal.getX(), node.point.getY()), goal);
        else return bestDis > Point.distance(new Point(node.point.getX(), goal.getY()), goal);
    }

}
