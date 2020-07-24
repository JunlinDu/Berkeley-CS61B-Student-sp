package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void TestAdd () {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("1", 1);
        assertEquals("1", pq.getSmallest());
        pq.add("2", 2.0);
        pq.add("3", 3.0);
        pq.add("4", 4.0);
        pq.add("5", 5.0);
        pq.add("6", 6.0);
        pq.add("7", 7.0);
        pq.add("0", 0.0);
        assertEquals("0", pq.getSmallest());
    }
}
