/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egimaben.skiena.starter;

/**
 *
 * @author egimaben
 */
/*
Consider the following algorithm to generate a sequence of numbers. Start with an
integer n. If n is even, divide by 2. If n is odd, multiply by 3 and add 1. Repeat this
process with the new value of n, terminating when n = 1. For example, the following
sequence of numbers will be generated for n = 22:
22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
It is conjectured (but not yet proven) that this algorithm will terminate at n = 1 for
every integer n. Still, the conjecture holds for all integers up to at least 1, 000, 000.
For an input n, the cycle-length of n is the number of numbers generated up to and
including the 1. In the example above, the cycle length of 22 is 16. Given any two
numbers i and j, you are to determine the maximum cycle length over all numbers
between i and j, including both endpoints.
Input
The input will consist of a series of pairs of integers i and j, one pair of integers per
line. All integers will be less than 1,000,000 and greater than 0.
Output
For each pair of input integers i and j, output i, j in the same order in which they
appeared in the input and then the maximum cycle length for integers between and
including i and j. These three numbers should be separated by one space, with all three
numbers on one line and with one line of output for each line of input.
Sample Input 
1 10
100 200
201 210
900 1000 

Sample Output
1 10 20
100 200 125
201 210 89
900 1000 174
*/
public class CycleProblem {
//take input for end points of the integer range
    private static int getMaxCycleLength(int firstInt, int lastInt) {
        //var holds maximum cycle length found and updates for each value higher than the previous
        int maxLen = 1;
        //loop thru entire range of integers provided
        for (int i = firstInt; i <= lastInt; i++) {
            //holds i's current generated number in sequence which acts as a base for generating next number in sequence
            int sequenceInput = i;
            //cycle length tracker of i, to be compared with maxLen later after generating entire sequence
            int cycleLen = 0;
            //generate sequence for i
            do {
                //case when sequenceInput is even and then when it's odd
                if (sequenceInput % 2 == 0) {
                    sequenceInput = sequenceInput / 2;
                } else if (sequenceInput % 2 == 1) {
                    sequenceInput = 3 * sequenceInput + 1;
                }
                //increment the tracker since a new integer has been added to sequence
                cycleLen++;
                //run the while loop till a 1 is encountered
            } while (sequenceInput != 1);
            //ensure to track the last digit, 1, in the sequence
            if(sequenceInput==1)
                cycleLen++;
            //compare current cycle len with maxLen and update the latter if the former is the current highest
            if (cycleLen > maxLen) {
                maxLen = cycleLen;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int i=900;
        int j=1000;
        System.out.println("Max cycle len of "+i+" and "+j+" is "+getMaxCycleLength(i, j));
    }

}
