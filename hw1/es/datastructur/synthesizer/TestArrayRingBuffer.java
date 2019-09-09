package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Yun Hong
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(7);
        assertEquals(7, arb.capacity());
        assertEquals(true, arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3, arb.fillCount());
        arb.dequeue();
        arb.dequeue();
        assertEquals(1, arb.fillCount());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        assertEquals(true, arb.isFull());
        int actual = arb.peek();
        assertEquals(3, actual);
    }
}
