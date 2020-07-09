
package synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int cursorPosition;

        public ArrayRingBufferIterator() {
            cursorPosition = first;
        }

        @Override
        public boolean hasNext() {
            return cursorPosition != (capacity() - 1 + last) % capacity();
        }

        @Override
        public T next() {
            T currentItem = rb[cursorPosition];
            cursorPosition = (cursorPosition + 1) / capacity();
            return currentItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }


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
        if(isFull()){
            throw new RuntimeException("Ring Buffer Overflow");
        }
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
        if(isEmpty()){
            throw new RuntimeException("Ring Buffer Underflow");
        }
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

    public static void main(String[] args) {
        System.out.println(-1%6);
    }
}
