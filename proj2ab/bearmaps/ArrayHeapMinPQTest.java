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
        System.out.println("Total time elapsed for: ");
        System.out.println();
        int num = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i ++) pq.add("Hi" + Integer.valueOf(i).toString(), i);
        long end = System.currentTimeMillis();
        System.out.println("Add (ArrayHeapMinPQ): " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < num; i ++) npq.add("Hi" + Integer.valueOf(i).toString(), i);
        end = System.currentTimeMillis();
        System.out.println("Add (NativeImplementation): " + (end - start)/1000.0 +  " seconds.");



        System.out.println();
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) pq.changePriority("Hi" + i, num - i);
        end = System.currentTimeMillis();
        System.out.println("changePriority (ArrayHeapMinPQ): " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) npq.changePriority("Hi" + i, num - i);
        end = System.currentTimeMillis();
        System.out.println("changePriority (NativeImplementation): " + (end - start)/1000.0 +  " seconds.");



        System.out.println();
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) pq.contains("Hi" + i);
        end = System.currentTimeMillis();
        System.out.println("contains (ArrayHeapMinPQ): " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) npq.contains("Hi" + i);
        end = System.currentTimeMillis();
        System.out.println("contains (NativeImplementation): " + (end - start)/1000.0 +  " seconds.");

    }
}
