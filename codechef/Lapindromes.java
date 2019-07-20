/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Lapindromes
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		try {
		    
		    Scanner scanner = new Scanner(System.in);
		    
		    int tests = scanner.nextInt(), i, j, k, middle;
            String input;
            char[] charArray;
            int string1, string2;
		    
            for (i = 0; i < tests; i++) {

                input = scanner.next();
                middle = input.length() / 2;
                charArray = input.toCharArray();
                
                k = middle;
                string1 = 0;
                string2 = 0;
                if (input.length() % 2 == 1) {
                    // middle--;
                    k++;
                }
                
                for (j = 0; j < middle; j++, k++) {
                    
                    // System.out.println(charArray[j] + "--" + charArray[k]);
                    string1 += charArray[j];
                    string2 += charArray[k];
                }
                
                // System.out.println(string1 + "--" + string2);

                System.out.println(string1 == string2 ? "YES" : "NO");
            }
            
            scanner.close();
		    
		} catch (Exception e) {
            // System.out.println(e.getMessage());
		}
	}
}
