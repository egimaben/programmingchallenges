/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egimaben.skiena.starter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egimaben
 */
public class TheTrip {

    private static double getSettlementAmount(String[] expensesForAtrip) {
        double xdAmt = 0.00;
        double totalExpense = 0.0;
        for (String s : expensesForAtrip) {
            totalExpense += Double.valueOf(s);
        }
        double averageExpenseToEqualize = round(totalExpense / Double.parseDouble("" + expensesForAtrip.length), 2);
        String[] diffForEach = new String[expensesForAtrip.length];
        for (int i = 0; i < expensesForAtrip.length; i++) {
            double currentStudentActualExpense = Double.valueOf(expensesForAtrip[i]);
            double diffToEqualize = averageExpenseToEqualize - currentStudentActualExpense;

            double roundedVal = diffToEqualize >= 0 ? round(diffToEqualize, 2) : 0.00;
            double absValue = Math.abs(roundedVal);
            diffForEach[i] = "" + absValue;
        }
        //find total amount to exchange
        for (String s : diffForEach) {
            xdAmt += Double.valueOf(s);
        }
        return round(xdAmt, 2);
    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static String[] getSettlementAmounts(String rawExpenseData) {
        //raw data extracted as array line by line
        String[] inputArr = rawExpenseData.split("\n");
        String[] outputArr;
        //list of arrays, each array element a student's expense on a single trip
        List<String[]> fineExpenseDataList = new ArrayList<>();
        //var to hold number of studenst in each trip, helps us know how many lines of expense data to pick for each trip
        int numStudents = 0;
        //current trip data index in fineExpenseDataList
        int tripDataListIndex = -1;
        //int to hold current index in any new array
        int currIndex = -1;
        for (String line : inputArr) {
            if (line.equals("0")) {
                break;
            }
            if (!line.contains(".")) {
                numStudents = Integer.parseInt(line);
                String[] tripData = new String[numStudents];
                fineExpenseDataList.add(tripData);
                tripDataListIndex++;
                currIndex = -1;
            } else {
                fineExpenseDataList.get(tripDataListIndex)[++currIndex] = line;
            }

        }
        outputArr = new String[fineExpenseDataList.size()];
        for (int i = 0; i < fineExpenseDataList.size(); i++) {
            outputArr[i] = "" + getSettlementAmount(fineExpenseDataList.get(i));
        }
        return outputArr;
    }

    public static void main(String[] args) {
        System.out.println("starting...now");
        String input = ""
                + "3\n"
                + "10.00\n"
                + "20.00\n"
                + "30.00\n"
                + "4\n"
                + "15.00\n"
                + "15.01\n"
                + "3.00\n"
                + "3.01\n"
                + "0";
        String[] settlmentAmounts = getSettlementAmounts(input);
        for (String s : settlmentAmounts) {
            System.out.println("$" + s);
        }

    }
}
