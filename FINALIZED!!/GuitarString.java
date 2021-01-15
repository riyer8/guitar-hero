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
	int sample = 44100;
	// using sampling rate of 44,100

    public GuitarString(double frequency) {
    	length = 0;
		buffer = new RingBuffer((int) Math.ceil(sample / frequency));
		while(!buffer.isFull())
			buffer.enqueue(0);
    }

    public GuitarString(double[] init) {
    	length = 0;
		buffer = new RingBuffer(init.length);
		for(int i = 0; i < init.length; i++) {
			buffer.enqueue(init[i]);
		}
    }

    public int length() {
        return length;
    }

    public void pluck() {
    	for(int i = 0; i < buffer.capacity(); i++) {
			buffer.enqueue(Math.random() - 0.5);
		}
    }

    public void tic() {
		buffer.enqueue(.996 * (buffer.dequeue() + sample()) / 2 );
		length++;
    }

    public double sample() {
    	return buffer.peek();
    }
    public String toString() {
    	return length + " " +sample;
    }
    public static void main(String[] args) {
         double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
         GuitarString testString = new GuitarString(samples);
         for (int i = 0; i < samples.length; i++) {
             int t = testString.length();
             double sample = testString.sample();
             System.out.printf("%6d %8.4f\n", t, sample);
             testString.tic();
         }
    }

}