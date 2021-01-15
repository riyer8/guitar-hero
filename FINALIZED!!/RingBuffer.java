/******************************************************************************
 *  Name: Ramya and Jewell
 *  
 *  Description:  
 *
 * This is a template file for RingBuffer.java. It lists the constructors and
 * methods you need, along with descriptions of what they're supposed to do.
 *  
 * Note: it won't compile until you fill in the constructors and methods
 *       (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/
import java.util.*;

public class RingBuffer {
    private double buffer[];
    private int first, last, size, capacity;

    public RingBuffer(int capacity) {
    	size = 0;
        this.capacity = capacity;
        buffer = new double[capacity];
        first = 0;
        last = first;
    }

    public int capacity() {
    	return capacity;
    }

    public int size() {
    	 return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(double x) {
    	 if(last == capacity)
        {
        	last = 0;
        	enqueue(x);
         } else if(isFull()) {
        	buffer[last] = x;
        	if (first + 1 == capacity)
        		first = 0;
        	else
        		first++;
        	last++;
        } else {
        	buffer[last] = x;
        	last++;
        	size++;
        }
    }

    public double dequeue() {
    	double item = buffer[first];
        if(first == capacity - 1)
          	first = 0;
        else first++;
        size--;
        return item;

    }

    public double peek() {
    	return buffer[first];
    }
    public String toString() {

		double[] d = new double[size()]; 
		int begin = first;
		for(int i = 0; i < size(); i++ ) {
			d[i] = buffer[begin]; 
			begin++; 
			if(begin >= capacity) {
				begin -= capacity; 
			}
		}
		return Arrays.toString(d); 

	}
    // tests and calls every instance method in this class
    public static void main(String[] args) {
    	int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
          buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println(buffer.size());
        while (buffer.size() >= 2) {
          buffer.enqueue(buffer.dequeue() + buffer.dequeue());
        }
        System.out.println(buffer.peek());
    }

}