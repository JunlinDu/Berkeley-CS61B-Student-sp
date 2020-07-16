package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue("A");
        arb.enqueue("B");
        arb.enqueue("C");
        arb.enqueue("D");
        arb.enqueue("E");
        arb.enqueue("F");
        assertEquals("A", arb.peek());
        assertEquals(6, arb.fillCount());
        assertEquals("A", arb.dequeue());
        assertEquals(5, arb.fillCount());
        assertEquals("B", arb.dequeue());
        assertEquals(4, arb.fillCount());
        arb.enqueue("G");
        arb.enqueue("H");
        arb.enqueue("I");
        arb.enqueue("J");
        arb.enqueue("K");
        arb.enqueue("L");
        assertEquals(10, arb.fillCount());
        assertTrue(arb.isFull());
        assertFalse(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
