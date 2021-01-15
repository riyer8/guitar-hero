import java.util.*;

/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/
class Letter {
	char letter;
	double x, y;
	public Letter(char letter, double val) {
		this.letter = letter;
		this.x = val/38+.03;
		this.y = .9;
	}
	public String toString() {
		return letter+ " " + x + " " + y;
	}
}
public class GuitarHeroLite3 {
	static Stopwatch stopwatch;
	static double[] values;
	static GuitarString[] strings;
	static Map<Character, GuitarString> stringvals;
	public static void main(String[] args) {StdDraw.setCanvasSize(550,550);
		stringvals = new HashMap<Character, GuitarString>();
		values = new double[37];
		strings = new GuitarString[37];
		ArrayList<Letter> arr = new ArrayList<Letter>();
		for (int i = 0; i<37; i++) {
			//values[i] = 110*Math.pow(2, i/12.0);
			values[i] = 440*Math.pow(2, (i-12)/12.0);
			strings[i] = new GuitarString(values[i]);
			char tempchar = ' ';
			if (i<10) { //makes keys
				tempchar = (char)(i+48);
				stringvals.put((char)(i+48), strings[i]);
			}
			else if (i == 36) {
				tempchar = ';';
				stringvals.put(';', strings[i]);
			}
			else {
				tempchar = (char)(i+87);
				stringvals.put((char)(i+87), strings[i]);
			}
			arr.add(new Letter(tempchar,(double)i));
			// so I just made all the keys 0-9, a-z, and ; and i took out all
			// the code and made it concise. now the main thing
			//to do is to create the two polynomials and the letters falling.
		}
		System.out.println(stringvals);
        
        final double TEXT_POS_Y = .5;
        StdDraw.text(.5, TEXT_POS_Y, "Type 's' to start");
        while(true) {	  
	        if (StdDraw.hasNextKeyTyped()) {
	        	char key = StdDraw.nextKeyTyped();
	        	if (key == 's') {
	        		StdDraw.clear();
	        		break;
	        	}
	        }
        }
		play(arr);
    }
    
    private static void play(ArrayList<Letter> arr) {
    	StdDraw.enableDoubleBuffering(); 
    	for (int q = 0; q<arr.size(); q++) {
    		while (true) {
    		//System.out.println(stopwatch.elapsedTime());
    		if(stopwatch.elapsedTime() > 1) { 
            		stopwatch = new Stopwatch();
            	    StdDraw.clear();
            	    StdDraw.setPenColor(StdDraw.BLACK);  //base lines
        			StdDraw.line(0,.15,1,.15);
        			StdDraw.line(0,.1,1,.1);
        			StdDraw.line(0,.2,1,.2);
	        		for(Letter t : arr) {
	        			t.y -= 0.04;
	        			StdDraw.setPenColor(StdDraw.BLACK);
	        			StdDraw.text(t.x,t.y,t.letter+"");
	        		}
	        		StdDraw.show();
            }
            //StdDraw.clear();
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (arr.get(q).letter == key) { //if key is right
                	if (stringvals.containsKey(key)&&arr.get(q).y >=.1&&arr.get(q).y<=.2) { //if key is in range
                		stringvals.get(key).pluck();
                		StdDraw.setPenColor(StdDraw.GREEN); //correct - green line
            			StdDraw.line(0,.15,1,.15);
                		break;
                	}
                	else {
                		StdDraw.setPenColor(StdDraw.RED);
            			StdDraw.line(0,.15,1,.15); }//wrong - make red
                	}
                }
            }
            double sample = 0;
            for (int i = 0; i<37; i++) {
            	sample+=strings[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i<37; i++) {
            	strings[i].tic();
            }
            StdDraw.disableDoubleBuffering();
        }
   	}
        
 

}
class Stopwatch { 

    static long start;

    /**
     * Initializes a new Stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    } 


    /**
     * Returns the elapsed CPU time (in seconds) since the Stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the Stopwatch was created
     */
    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    
    /**
     * Unit tests the {@code Stopwatch} data type.
     * Takes a command-line argument {@code n} and computes the 
     * sum of the square roots of the first {@code n} positive integers,
     * first using {@code Math.sqrt()}, then using {@code Math.pow()}.
     * It prints to standard output the sum and the amount of time to
     * compute the sum. Note that the discrete sum can be approximated by
     * an integral - the sum should be approximately 2/3 * (n^(3/2) - 1).
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // sum of square roots of integers from 1 to n using Math.sqrt(x).
        Stopwatch timer1 = new Stopwatch();
        double sum1 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum1 += Math.sqrt(i);
        }
        double time1 = timer1.elapsedTime();
        System.out.printf("%e (%.2f seconds)\n", sum1, time1);

        // sum of square roots of integers from 1 to n using Math.pow(x, 0.5).
        Stopwatch timer2 = new Stopwatch();
        double sum2 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum2 += Math.pow(i, 0.5);
        }
        double time2 = timer2.elapsedTime();
        System.out.printf("%e (%.2f seconds)\n", sum2, time2);
    }
} 