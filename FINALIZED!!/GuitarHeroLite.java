import java.awt.*;
import java.util.*;
import java.io.*;

class Guitar { //this class contains the letter and coordinates. not sure if we should use the boolean but it's there too
	char letter;
	double x, y;
	public Guitar(char letter, double val) {
		this.letter = letter;
		this.x = val/38+.03;
		this.y = .9;
	}
	public String toString() {
		return letter+ " " + x + " " + y;
	}
}
class Printval { //this class is solely just to be able to print the char array in the map
	Guitar[] real;
	char[] arr;
	int count;
	public Printval(char[] arr, int count) {
		this.arr = arr;
		this.count = count;
		real = new Guitar[arr.length];
		for (int i = 0;i<arr.length; i++) {
			real[i] = new Guitar(arr[i],count);
		}
	}
	public String toString() {
		return count + " " + Arrays.toString(real);
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
public class GuitarHeroLite {
	static Stopwatch stopwatch; //timer
	static Map<Character, GuitarString> stringvals; //character/key --> note. used to check if key pressed is valid
	static Map<Integer, Printval> columns; //column number --> character inputs
	static GuitarString[] strings; //notes. easy for adding all values
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("Play.txt")); //file input
		StdDraw.setCanvasSize(1000,700); //bigger Canvas size
		stringvals = new HashMap<Character, GuitarString>();
		columns = new HashMap<Integer, Printval>();
		strings = new GuitarString[37];
		for (int i = 0; i<37; i++) {
			strings[i] = new GuitarString(440*Math.pow(2, (i-12)/12.0));
			if (i<10) { //0-9 characters
				stringvals.put((char)(i+48), strings[i]);
			}
			else if (i == 36) { //; character
				stringvals.put(';', strings[i]);
			}
			else {// a-z characters
				stringvals.put((char)(i+87), strings[i]);
			}
		}
		int count = 0; //counter to associate column number to array
		int notes = 0; //length of the input char[] 
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			char[] arr = s.substring(1,s.length()-1).toCharArray();
			columns.put(count, new Printval(arr, count));
			count++;
			notes = arr.length;
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
		play(columns, notes);
    }
    private static void play(Map<Integer, Printval> col, int val) {
    	StdDraw.enableDoubleBuffering();
		int count = 0; //position we are at in the printval arrays
		ArrayList<Guitar> all = new ArrayList<Guitar>(); //Guitars that are currently on-screen
		ArrayList<Character> allreal = new ArrayList<Character>(); //Character that are currently on-screen
		boolean ok = false; //used just to make sure there was something printed before
		int score = 0; //number of keys correctly typed
    	while (true) {  
    		if(stopwatch.elapsedTime() > 1.5) {
    			if (count<val) {
	    			for (int a: col.keySet()) {
	    				if (col.get(a).arr[count] != ' ') {
	    					all.add(col.get(a).real[count]);
	    					allreal.add(col.get(a).arr[count]);
	    				}
	    			}
    			}
        		stopwatch = new Stopwatch();
        	    StdDraw.clear();
	    		StdDraw.line(0,.15,1,.15);
	    		StdDraw.setPenColor(Color.BLACK);
	    		StdDraw.line(0, .2, 1, .2);
	    		StdDraw.line(0, .1, 1, .1);
        		for(int i = 0; i<all.size(); i++) {
        			Guitar t = all.get(i);
        			t.y -= 0.05;
        			if (t.y<0) {
        				all.remove(i);
        				allreal.remove(i);
        				i--;
        			}
        			else {
	        			StdDraw.setPenColor(StdDraw.BLACK);
	        			StdDraw.text(t.x,t.y,t.letter+"");
        			}
        		}
        		StdDraw.show();
        		count++;
	         }
    		 if (StdDraw.hasNextKeyTyped()) {
                 char key = StdDraw.nextKeyTyped();
                 int ind = allreal.indexOf(key);
             	if (ind>=0 && stringvals.containsKey(key)&&all.get(ind).y >=.1&&all.get(ind).y<=.2) { //if key is in range
             		stringvals.get(key).pluck();
             		StdDraw.setPenColor(StdDraw.GREEN); //correct - green line
         			StdDraw.line(0,.15,1,.15);
         			allreal.remove(ind);
         			all.remove(ind);
         			score++;
         			if (allreal.size() == 0) break;
             	}
             	else {
             		StdDraw.setPenColor(StdDraw.RED);
         			StdDraw.line(0,.15,1,.15); 
         		}//wrong - make red
            }
            double sample = 0;
            for (int i = 0; i<37; i++) {
            	sample+=strings[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i<37; i++) {
            	strings[i].tic();
            }
            if (allreal.size()>0) ok = true;
            if (ok && allreal.size() == 0) break;
		}
    	StdDraw.clear();
    	StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    	StdDraw.text(.5, .5, "Finished!");
    	StdDraw.text(.5, .4, "You got " + score + " key right!");
    	 StdDraw.show();
   }
}
