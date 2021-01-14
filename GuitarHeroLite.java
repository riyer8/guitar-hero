import java.awt.Color;
import java.util.*;

class Guitar {
	char letter;
	double x, y;
	boolean gone;
	public Guitar(char letter, double val) {
		this.letter = letter;
		this.x = val/38+.03;
		this.y = .9;
	}
	public String toString() {
		return letter+ " " + x + " " + y;
	}
}
class Pair {
	Color col;
	Queue<Guitar> qu;
	public Pair(Queue<Guitar> qu, Color col) {
		this.qu = qu;
		this.col = col;
	}
	public String toString() {
		return col + " " + qu;
	}
}
public class GuitarHeroLite {
	static Stopwatch stopwatch;
	static Map<Character, GuitarString> stringvals;
	static Map<Integer, Pair> columns;
	static ArrayList<Guitar> arr;
	static GuitarString[] strings;
	public static void main(String[] args) {
		Scanner sc = new Scanner("Play.txt");
		StdDraw.setCanvasSize(1000,700);
		stringvals = new HashMap<Character, GuitarString>();
		arr = new ArrayList<Guitar>();
		columns = new HashMap<Integer, Pair>();
		strings = new GuitarString[37];
		int count = 0;
		for (int i = 0; i<37; i++) {
			//values[i] = 110*Math.pow(2, i/12.0);
			strings[i] = new GuitarString(440*Math.pow(2, (i-12)/12.0));
			char tempchar = ' ';
			if (i<10) {
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
			arr.add(new Guitar(tempchar,(double)i));
		}
		
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
    private static void play(ArrayList<Guitar> arr) {
    	StdDraw.enableDoubleBuffering();
    	for (int q = 0; q<arr.size(); q++) {
    		
    		for(int i = 0; i < 37; i++) {
				StdDraw.setPenColor(Color.GRAY);
				StdDraw.line(i/37.0,0,i/37.0, 1);
			}
    		while (true) {
	    		if(stopwatch.elapsedTime() > 1.5) { 
	            		stopwatch = new Stopwatch();
	            	    StdDraw.clear();
		        		for(Guitar t : arr) {
		        			t.y -= 0.05;
		        			StdDraw.setPenColor(StdDraw.BLACK);
		        			StdDraw.text(t.x,t.y,t.letter+"");
		        		}
		        		StdDraw.show();
	            }
	            if (StdDraw.hasNextKeyTyped()) {
	                char key = StdDraw.nextKeyTyped();
	                if (arr.get(q).letter == key) {
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
	            StdDraw.disableDoubleBuffering();
    		}
    	}
   }
}
class Stopwatch { 
    static long start;
    public Stopwatch() {
        start = System.currentTimeMillis();
    }
    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
} 