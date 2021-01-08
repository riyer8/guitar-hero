/******************************************************************************
 *  Name: Ramya and Jewell
 *  
 * Description: 
 * This is a template file for GuitarString.java. It lists the constructors
 * and methods you need, along with descriptions of what they're supposed
 * to do.
 *  
 * Note: it won't compile until you fill in the constructors and methods
 *       (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {
		RingBuffer buffer;
		int length;
		int samp = 44100;
		// using sampling rate of 44,100
    public GuitarString(double frequency) {
    	length = 0;
		buffer = new RingBuffer((int) Math.ceil(samp / frequency));
		while(!buffer.isFull())
			buffer.enqueue(0);
    }

    public GuitarString(double[] init) {
    	length = 0;
		buffer = new RingBuffer(init.length);
		for(int i = 0; i<init.length; i++) {
			buffer.enqueue(init[i]);
		}
    }

    public int length() {
        return length;
    }

    public void pluck() {
    	for(int i = 0; i<buffer.capacity; i++) {
			buffer.enqueue(Math.random() - 0.5);
		}
    }

    public void tic() {
		buffer.enqueue(.99 * (buffer.dequeue() + sample()) / 2 );
		length++;
    }

    public double sample() {
    	return buffer.peek();
    }
    
    public static void main(String[] args) {
         double[] arr = {.1,.2,.3,.4,-.1,-.4,-.3,.5};  
         GuitarString guitartest = new GuitarString(arr);
         for (int i = 0; i < arr.length; i++) {
             int t = guitartest.length();
             double samp = guitartest.sample();
             System.out.println(t);
             System.out.println(samp);
             System.out.println("--------------");
             guitartest.tic();
         }
    }

}
