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
public class CycleProblem {

    private static int getMaxCycleLength(int firstInt, int lastInt) {
        int maxLen = 1;
        for (int i = firstInt; i <= lastInt; i++) {
            int sequenceInput = i;
            int cycleLen = 0;
            do {
                if (sequenceInput % 2 == 0) {
                    sequenceInput = sequenceInput / 2;
                } else if (sequenceInput % 2 == 1) {
                    sequenceInput = 3 * sequenceInput + 1;
                }
                cycleLen++;
            } while (sequenceInput != 1);
            if(sequenceInput==1)
                cycleLen++;
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
