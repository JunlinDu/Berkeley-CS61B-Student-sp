
package synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = this.last = this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        this.rb[last] = x;
        fillCount++;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer, and returns
     * the dequeued item If the buffer is empty, then throw
     * new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        T firstItem = this.rb[first];
        this.rb[first] = null;
        fillCount--;
        first = (first + 1) % capacity;
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return this.rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    public static void main(String[] args) {

    }
}
