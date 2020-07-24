package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void TestAdd () {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();

        pq.add("1", 1);
        assertEquals("1", pq.getSmallest());

        pq.add("2", 2.0);
        pq.add("3", 3.0);
        pq.add("4", 4.0);
        pq.add("0", 0.0);
        assertEquals("0", pq.getSmallest());
    }

    @Test
    public void TestRemoveSmallest () {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("1", 1);
        pq.add("2", 2.0);
        pq.removeSmallest();
        assertEquals("2", pq.getSmallest());

        pq.add("3", 3.0);
        pq.add("4", 4.0);
        pq.add("5", 5.0);
        pq.add("0", 0.0);
        pq.removeSmallest();
        assertEquals("2", pq.getSmallest());
    }

    @Test
    public void TestRuntime() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> npq = new NaiveMinPQ<>();

        int num = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i ++) {
            pq.add("Hi" + Integer.valueOf(i).toString(), i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed (ArrayHeapMinPQ): " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i ++) {
            npq.add("Hi" + Integer.valueOf(i).toString(), i);
        }
        end = System.currentTimeMillis();
        System.out.println("Total time elapsed (NativeImplementation): " + (end - start)/1000.0 +  " seconds.");

    }
}
