package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull())
            throw new RuntimeException("Ring buffer overflow.");
        else {
            rb[last] = x;
            last = (last + 1) % capacity();
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        T returnFirst;
        if (isEmpty())
            throw new RuntimeException("Ring buffer underflow.");
        else {
            returnFirst = peek();
            fillCount -= 1;
            first = (first + 1) % capacity();
        }
        return returnFirst;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Ring buffer underflow.");
        else
            return rb[first];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    /** nested class */
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        public ArrayRingBufferIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < fillCount();
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.capacity() != other.capacity())
            return false;
        if (this.fillCount() != other.fillCount())
            return false;
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (thisIterator.next() != otherIterator.next())
                return false;
        }
        return true;
    }

}
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    // TODO: Remove all comments that say TODO when you're done.
