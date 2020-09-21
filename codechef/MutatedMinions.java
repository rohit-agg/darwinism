// package codechef;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class MutatedMinions
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    try {
	        // your code goes here
			Scanner scan = new Scanner(System.in);
			
			int trial = 100000;
			System.out.println(trial);
		
		    int t = scan.nextInt();
		    int n, k, i, j, out, val;
		    
			for (i = 0; i < t; i++) {

				n = scan.nextInt();
				t = scan.nextInt();
				
				out = 0;

				for (j = 0; j < n; j++) {

					val = scan.nextInt();
					val += t;
					if (val % 7 == 0) {
						out++;
					}
				}

				System.out.println(out);
			}
			scan.close();
		    
	    } catch (Exception e) {
			// System.out.println("<-->" + e.getMessage());
	    }
	}
}
