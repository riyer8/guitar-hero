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
		play();
    }
    
    private static void play() {
    	while (true) {
            // pluck the corresponding string
    		/*
                String test = "my letters appear one by one";
                for (int i = 0; i < test.length(); i++) {
                    System.out.print(test.charAt(i));
                    try {
                        Thread.sleep(1000L);    // time interval in milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                */
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
            	
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                
                if (key == 'a') stringA.pluck();
                else if (key == 'b') stringAB.pluck();
                else if (key == 'c') stringB.pluck();
                else if (key == 'd') stringC.pluck();
                else if (key == 'e') stringCD.pluck();
                else if (key == 'f') stringD.pluck();
                else if (key == 'g') stringDE.pluck();
                else if (key == 'h') stringE.pluck();
                else if (key == 'i') stringF.pluck();
                else if (key == 'j') stringFG.pluck();
                else if (key == 'k') stringG.pluck();
                else if (key == 'l') stringGA.pluck();
                else if (key == 'm') stringAH.pluck();
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
        
    }

}
