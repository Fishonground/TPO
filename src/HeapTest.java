import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

public class HeapTest {

    public FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<Integer>();

    @Before
    public void setup(){
        fibonacciHeap.enqueue(0,1);
        fibonacciHeap.enqueue(1,2);
        fibonacciHeap.enqueue(2,3);
        fibonacciHeap.enqueue(3,1);
        fibonacciHeap.enqueue(4,2);
        fibonacciHeap.enqueue(5,3);
        //fibonacciHeap.dequeueMin();
    }
    @Test
    public void TestIsEmpty(){
        assertFalse("Не пустое!", fibonacciHeap.isEmpty());
    }
    @Test
    public void TestMin(){
        assertTrue("Не минимальное!", fibonacciHeap.min().getValue().equals(3) && fibonacciHeap.min().getPriority()==1);
    }
    /*
    @BeforeClass
    public static void Test(){
        System.out.println("Nothing!");
    }*/
    @Test
    public void TestDelMin(){
        fibonacciHeap.dequeueMin();
        assertEquals("Не минимальное!", 1.0, fibonacciHeap.min().getPriority());
    }

    @Test
    public void TestEnqueMin(){
        double min = fibonacciHeap.min().getPriority();
        fibonacciHeap.enqueue(14, -1000);
        double min2 = fibonacciHeap.min().getPriority();
        assertTrue("не минимальное!", min2<min && min2 == -1000);
    }
    @Test
    public void TestEnqueNotMin(){
        double min = fibonacciHeap.min().getPriority();
        fibonacciHeap.enqueue(14, 1000);
        double min2 = fibonacciHeap.min().getPriority();
        assertTrue("Минимальное!", min2 == min);
    }

    @Test
    public void TestSize(){
        int was = fibonacciHeap.size();
        fibonacciHeap.enqueue(11, 5);
        int res = fibonacciHeap.size();
        assertTrue("Неверный размер!", res==was+1);
    }
    @Test
    public void TestChangingValue(){
        FibonacciHeap.Entry was = fibonacciHeap.min();
        fibonacciHeap.decreaseKey(fibonacciHeap.min(), was.getPriority()- 1);
        int res = fibonacciHeap.min().getValue();
        assertSame("Не одинаковые!", was.getValue(), res);
    }
}
