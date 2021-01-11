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

public class GuitarHeroLite {
	static double[] values;
	static GuitarString[] strings;
	static Map<Character, GuitarString> stringvals;
	public static void main(String[] args) {
		stringvals = new HashMap<Character, GuitarString>();
		values = new double[37];
		strings = new GuitarString[37];
		for (int i = 0; i<37; i++) {
			//values[i] = 110*Math.pow(2, i/12.0);
			values[i] = 440*Math.pow(2, (i-12)/12.0);
			strings[i] = new GuitarString(values[i]);
			if (i<10) {
				stringvals.put((char)(i+48), strings[i]);
			}
			else if (i == 36) stringvals.put(';', strings[i]);
			else {
				stringvals.put((char)(i+87), strings[i]);
			}
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
        ArrayList<String> arr = new ArrayList<String>();
        for (char a: stringvals.keySet()) {
        	arr.add(a+"");
        }
		play(arr);
    }
    
    private static void play(ArrayList<String> arr) {
    	for (int q = 0; q<arr.size(); q++) {
    		char value = arr.get(q).charAt(0);
    		StdDraw.text(.5, .5, value+"");
    		while (true) {
            if (StdDraw.hasNextKeyTyped()) {
            	
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if (value == key) {
                	if (stringvals.containsKey(key)) {
                		stringvals.get(key).pluck();
                		break;
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
        }
    	StdDraw.clear();
    	}
        
    }

}
/*
class Test {
    
    public class letter{
        String key;
        double x;
        double y;
        
        public letter(String key, double x, double y) {
            this.key=key;
            this.x = x;
            this.y = y;
        }
    }
    
    public class Stopwatch { 
        private final long start;
        public Stopwatch() {
            start = System.currentTimeMillis();
        } 
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (    now - start) / 1000.0;
        }
    }
    
    public static void main(String[] args)throws Exception {
        new Test().run();
    }                                               
    
    public void run() throws Exception {
        Stopwatch s = new Stopwatch();
        StdDraw.setCanvasSize(700, 700);
        letter t = new letter("t", 0,1);
        
        boolean temp = true;
        while(temp) {
            if(s.elapsedTime() > 0.11) {
                s = new Stopwatch();
                
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledCircle(t.x,  t.y,  0.015);
                
                t.y -= 0.015;
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(t.x, t.y, t.key);
                
                if(t.y < -5) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(t.x, t.y, t.key);
                    temp = false;
                }
            }
        }
    }
}
*/