package byog.Tests;

import edu.princeton.cs.algs4.StdDraw;

public class TestStdDraw {
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenRadius(0.01);
        StdDraw.filledCircle(0.8,0.8, 0.1);
        StdDraw.circle(0.2,0.1, 0.05);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
    }
}
