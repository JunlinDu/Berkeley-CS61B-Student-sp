package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    private static final long SEED = 2873163;
    private static final Random RANDOM = new Random(SEED);

    @Test
    public void runTimeCompare() {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            points.add(new Point(randomDouble(), randomDouble()));
        }

        NaivePointSet nn = new NaivePointSet(points);
        KDTree kdTree = new KDTree(points);

        System.out.println();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) kdTree.nearest(randomDouble(), randomDouble());
        long end = System.currentTimeMillis();
        System.out.println("nearest() operation time (KDTree): " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) nn.nearest(randomDouble(), randomDouble());
        end = System.currentTimeMillis();
        System.out.println("nearest() operation time (NativePointSet): " + (end - start)/1000.0 +  " seconds.");
    }

    public double randomDouble() {
        return RANDOM.nextDouble() * 20 - 10;
    }
}
