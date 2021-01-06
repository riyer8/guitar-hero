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
	static GuitarString stringDE, stringE, stringF, stringFG,stringG, stringGA,stringAH; 
	static double[] values;
	static GuitarString[] strings;
	public static void main(String[] args) {
		
		values = new double[13];
		strings = new GuitarString[13];
		for (int i = 0; i<13; i++) {
			values[i] = 440*Math.pow(2, i/12.0);
			strings[i] = new GuitarString(values[i]);
		}
        stringA = strings[0]; // a
        stringAB = strings[1]; //b
        stringB = strings[2]; // c
        stringC =strings[3]; // d
        stringCD = strings[4]; // e
        stringD = strings[5]; // f
        stringDE = strings[6]; // g
        stringE = strings[7]; // h
        stringF = strings[8]; // i
        stringFG = strings[9]; // j
        stringG = strings[10]; // k
        stringGA = strings[11]; // l
        stringAH = strings[12]; // m
        
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
        char[] musicnotes = new char[50];
        for (int i = 'a'; i<50+'a'; i++) {
        	musicnotes [i-'a'] = (char)('a'+((i-'a')%26));
        }
        System.out.println(Arrays.toString(musicnotes));
		play(musicnotes);
    }
    
    private static void play(char[] arr) {
    	for (int q = 0; q<arr.length; q++) {
            // pluck the corresponding string
    		char value = arr[q];
            // check if the user has typed a key, and, if so, process it
    		StdDraw.text(.5, .5, value+"");
    		while (true) {
            if (StdDraw.hasNextKeyTyped()) {
            	
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                
                if (key == 'a') {
                	stringA.pluck();
                	break;
                }
                else if (key == 'b') {
                	stringAB.pluck();break;
                }
                else if (key == 'c') {
                	stringB.pluck();break;
                }
                else if (key == 'd') {
                	stringC.pluck();break;
                }
                else if (key == 'e') {
                	stringCD.pluck();break;
                }
                else if (key == 'f') {
                	stringD.pluck();break;
                }
                else if (key == 'g') {
                	stringDE.pluck();break;
                }
                else if (key == 'h') {
                	stringE.pluck();break;
                }
                else if (key == 'i') {
                	stringF.pluck();break;
                }
                else if (key == 'j') {
                	stringFG.pluck();break;
                }
                else if (key == 'k') {
                	stringG.pluck();break;
                }
                else if (key == 'l') {
                	stringGA.pluck();break;
                }
                else if (key == 'm') {
                	stringAH.pluck();break;
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i<13; i++) {
            	sample+=strings[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);
            
            // advance the simulation of each guitar string by one step
            for (int i = 0; i<13; i++) {
            	strings[i].tic();
            }
        }
    		StdDraw.clear();
    	}
        
    }

}
