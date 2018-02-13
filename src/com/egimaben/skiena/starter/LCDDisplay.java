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
public class LCDDisplay {

    private static String generateElements(int size, String[] digits) {
        //number of rows of LCD elements we need to generate based on size
        int numRows = 2 * size + 3;
        //which will be the mid row
        int mid = (numRows / 2);
        //sb to hold results for this input
        StringBuilder lcdDisplay = new StringBuilder();
        for (int i = 0; i < numRows; i++) {

            if (i == 0) {
                lcdDisplay.append(generateTopRow(size, digits));
            } else if (i == (numRows - 1)) {
                lcdDisplay.append(generateBottomRow(size, digits));
            } else if (i == mid) {
                lcdDisplay.append(generateMidSection(size, digits));
            } else if (i > 0 && i < mid) {
                lcdDisplay.append(generateUpperSection(size, digits));
            } else if (i > mid && i < (numRows - 1)) {
                lcdDisplay.append(generateLowerSection(size, digits));
            }
            lcdDisplay.append("\n");
            //check if row is mid section
        }
        return lcdDisplay.toString();
    }

    private static String generateTopRow(int size, String[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            if (i > 0) {
                sb.append(" ");
            }
            //insert first space denoted by s+2
            sb.append(" ");
            String token;
            if (digit == 1 || digit == 4) {
                token = " ";
            } else {
                token = "-";
            }
            for (int j = 0; j < size; j++) {
                sb.append(token);
            }
            //insert the second space denoted by s+2
            sb.append(" ");

        }

        return sb.toString();
    }

    private static String generateUpperSection(int size, String[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            if (i > 0) {
                sb.append(" ");
            }
            if ((digit == 0 || digit == 4 || digit == 5 || digit == 6 || digit == 8 || digit == 9)) {
                sb.append("|");
            } else {
                sb.append(" ");
            }
            for (int j = 0; j < size; j++) {
                sb.append(" ");
            }
            if ((digit == 0 || digit == 1 || digit == 2 || digit == 3 || digit == 4 || digit == 7 || digit == 8 || digit == 9)) {
                sb.append("|");
            } else {
                sb.append(" ");
            }

        }

        return sb.toString();
    }

    private static String generateMidSection(int size, String[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            if (i > 0) {
                sb.append(" ");
            }
            //insert the first space denoted by s+2
            sb.append(" ");
            String token;
            if (digit == 0 || digit == 1 || digit == 7) {
                token = " ";
            } else {
                token = "-";
            }
            for (int j = 0; j < size; j++) {
                sb.append(token);
            }
            //insert the second space denoted by s+2
            sb.append(" ");

        }

        return sb.toString();
    }

    private static String generateLowerSection(int size, String[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            if (i > 0) {
                sb.append(" ");
            }
            if ((digit == 0 || digit == 2 || digit == 6 || digit == 8)) {
                sb.append("|");
            } else {
                sb.append(" ");
            }
            for (int j = 0; j < size; j++) {
                sb.append(" ");
            }
            if ((digit == 0 || digit == 1 || digit == 3 || digit == 4 || digit == 5 || digit == 6 || digit == 7 || digit == 8 || digit == 9)) {
                sb.append("|");
            } else {
                sb.append(" ");
            }

        }

        return sb.toString();
    }

    private static String generateBottomRow(int size, String[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            //create space between digits if starting new digit
            if (i > 0) {
                sb.append(" ");
            }
            //insert the first space denoted by s+2
            sb.append(" ");
            String token;
            if (digit == 1 || digit == 4 || digit == 7) {
                token = " ";
            } else {
                token = "-";
            }
            for (int j = 0; j < size; j++) {
                sb.append(token);
            }
            //insert the second space denoted by s+2
            sb.append(" ");

        }

        return sb.toString();
    }
/**
 * first method invoked with raw input, it cleans up the input and
 * generates LCD display for each line, combines the results and returns a single string
 * @param input raw input
 * @return 
 */
    private static String generateLCD(String input) {
        //split the input into an array such that each element corresponds to a line of input
        String[] inputLines = input.split("\n");
        //sb will hold overall result to display
        StringBuilder sb = new StringBuilder();
        /**
         * for each line, determine whether to end loop based on 0 0 input or to process the input
         */
        for (String line : inputLines) {
            String[] lineData = line.split(" ");
            if (lineData[0].equals("0") && lineData[1].equals("0")) {
                break;
            } else {
                //for line to be processed, get size input and string of numbers
                int s = Integer.parseInt(lineData[0]);
                String n = lineData[1];
                //array to hold string of numbers
                String[] nArr = new String[n.length()];
                //loop thru and populate array with digits
                for (int i = 0; i < n.length(); i++) {
                    nArr[i] = Character.toString(n.charAt(i));
                }
                //we are ready to generate LCD display elements, fire, then append to sb
                String elements = generateElements(s, nArr);
                sb.append(elements);
                sb.append("\n");

            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = ""
                + "1 123456789\n"
                + "2 123456789\n"
                + "3 123456789\n"
                + "4 123456789\n"
                + "0 0";
        
        System.out.println(generateLCD(input));
    }
}
