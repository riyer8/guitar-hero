import java.util.ArrayList;
import java.util.Arrays;

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
	static GuitarString stringA, stringAB, stringB, stringC, stringCD, stringD;
	static GuitarString stringDE, stringE, stringF, stringFG,stringG, stringGA; 
	static GuitarString stringA2,stringAB2, stringB2, stringC2, stringCD2, stringD2;
	static GuitarString stringDE2, stringE2, stringF2, stringFG2, stringG2, stringGA2;
	static GuitarString stringA3,stringAB3, stringB3, stringC3, stringCD3, stringD3;
	static GuitarString stringDE3, stringE3, stringF3, stringFG3, stringG3, stringGA3, stringA4; 
	static double[] values;
	static GuitarString[] strings;
	public static void main(String[] args) {
		
		values = new double[37];
		strings = new GuitarString[37];
		for (int i = 0; i<37; i++) {
			//values[i] = 110*Math.pow(2, i/12.0);
			values[i] = 440*Math.pow(2, (i-12)/12.0);
			strings[i] = new GuitarString(values[i]);
		}
		
        stringA = strings[0]; // a -- 3
        stringAB = strings[1]; //a#
        stringB = strings[2]; // b
        stringC =strings[3]; // c
        stringCD = strings[4]; // c#
        stringD = strings[5]; // d
        stringDE = strings[6]; // d#
        stringE = strings[7]; // e
        stringF = strings[8]; // f
        stringFG = strings[9]; // f#
        stringG = strings[10]; // g
        stringGA = strings[11]; // g#
        
        stringA2 = strings[12]; // a -- 4
        stringAB2 = strings[13]; //a#
        stringB2 = strings[14]; // b
        stringC2 =strings[15]; // c
        stringCD2 = strings[16]; // c#
        stringD2 = strings[17]; // d
        stringDE2 = strings[18]; // d#
        stringE2 = strings[19]; // e
        stringF2 = strings[20]; // f
        stringFG2 = strings[21]; // f#
        stringG2 = strings[22]; // g
        stringGA2 = strings[23]; // g#
        
        stringA3 = strings[24]; // a -- 5
        stringAB3 = strings[25]; //a#
        stringB3 = strings[26]; // b
        stringC3 =strings[27]; // c
        stringCD3 = strings[28]; // c#
        stringD3 = strings[29]; // d
        stringDE3 = strings[30]; // d#
        stringE3 = strings[31]; // e
        stringF3 = strings[32]; // f
        stringFG3 = strings[33]; // f#
        stringG3 = strings[34]; // g
        stringGA3 = strings[35]; // g#
        
        stringA4 = strings[36]; // a -- 6
        
        
        
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
        arr.add("q"); arr.add("2"); arr.add("w"); arr.add("e");
        arr.add("4"); arr.add("r"); arr.add("5"); arr.add("t");
        arr.add("y"); arr.add("7"); arr.add("u"); arr.add("g");
        arr.add("b"); arr.add("c"); arr.add("d");
        arr.add("c"); arr.add("d"); arr.add("e");
        arr.add("b"); arr.add("f"); arr.add("g"); arr.add("e");
        arr.add("f"); arr.add("g"); arr.add("\'");
        
		play(arr);
    }
    
    private static void play(ArrayList<String> arr) {
    	for (int q = 0; q<arr.size(); q++) {
            // pluck the corresponding string
    		char value = arr.get(q).charAt(0);
            // check if the user has typed a key, and, if so, process it
    		
    		StdDraw.text(.5, .5, value+"");
    		while (true) {
            if (StdDraw.hasNextKeyTyped()) {
            	
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if (value == key) {
	                if (key == 'q') {
	                	stringA.pluck();
	                	break;
	                }
	                else if (key == '2') {
	                	stringAB.pluck();break;
	                }
	                else if (key == 'w') {
	                	stringB.pluck();break;
	                }
	                else if (key == 'e') {
	                	stringC.pluck();break;
	                }
	                else if (key == '4') {
	                	stringCD.pluck();break;
	                }
	                else if (key == 'r') {
	                	stringD.pluck();break;
	                }
	                else if (key == '5') {
	                	stringDE.pluck();break;
	                }
	                else if (key == 't') {
	                	stringE.pluck();break;
	                }
	                else if (key == 'y') {
	                	stringF.pluck();break;
	                }
	                else if (key == '7') {
	                	stringFG.pluck();break;
	                }
	                else if (key == 'u') {
	                	stringG.pluck();break;
	                }
	                else if (key == '8') {
	                	stringGA.pluck();break;
	                }
	                else if (key == 'i') {stringA2.pluck();break;}
	                else if (key == '9') {stringAB2.pluck();break;}
	                else if (key == 'o') {stringB2.pluck();break;}
	                else if (key == 'p') {stringC2.pluck();break;}
	                else if (key == '-') {stringCD2.pluck();break;}
	                else if (key == '[') {stringD2.pluck();break;}
	                else if (key == '=') {stringDE2.pluck();break;}
	                else if (key == 'z') {stringE2.pluck();break;}
	                else if (key == 'x') {stringF2.pluck();break;}
	                else if (key == 'd') {stringFG2.pluck();break;}
	                else if (key == 'c') {stringG2.pluck();break;}
	                else if (key == 'f') {stringGA2.pluck();break;}
	                
	                else if (key == 'v') {stringA3.pluck();break;}
	                else if (key == 'g') {stringAB3.pluck();break;}
	                else if (key == 'b') {stringB3.pluck();break;}
	                else if (key == 'n') {stringC3.pluck();break;}
	                else if (key == 'j') {stringCD3.pluck();break;}
	                else if (key == 'm') {stringD3.pluck();break;}
	                else if (key == 'k') {stringDE3.pluck();break;}
	                else if (key == ',') {stringE3.pluck();break;}
	                else if (key == '.') {stringF3.pluck();break;}
	                else if (key == ';') {stringFG3.pluck();break;}
	                else if (key == '/') {stringG3.pluck();break;}
	                else if (key == '\'') {stringGA3.pluck();break;}
	                
	                else if (key == '\\') {stringA4.pluck();break;}
	                
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i<37; i++) {
            	sample+=strings[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);
            
            // advance the simulation of each guitar string by one step
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