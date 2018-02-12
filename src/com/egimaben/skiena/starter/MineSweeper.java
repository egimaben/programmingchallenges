package com.egimaben.skiena.starter;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author egimaben
 */
public class MineSweeper {

    private static List<String[][]> createMineFields(String rawInput) {
        List<String[][]> mineFields = new ArrayList<>();
        int rows = 0;
        int cols = 0;
        int fieldIndex = -1;
        int rowInField = 0;

        String[] splitRawData = rawInput.split("\n");
        for (String line : splitRawData) {
            String[] tmpArr = line.split(" ");
            if (tmpArr.length == 2) {
                if (tmpArr[0].equals("0") && tmpArr[1].equals("0")) {
                    break;
                } else {
                    rows = Integer.parseInt(tmpArr[0]);
                    cols = Integer.parseInt(tmpArr[1]);
                    mineFields.add(new String[rows][cols]);
                    fieldIndex++;
                    rowInField = 0;

                }
            } else {
                String[][] currField = mineFields.get(fieldIndex);
                for (int k = 0; k < cols; k++) {
                    currField[rowInField][k] = Character.toString(line.charAt(k));
                }
                rowInField++;
            }
        }
        return mineFields;
    }

    private static void detectMineCount(String[][] mineField) {
        for (int i = 0, len = mineField.length - 1; i <= len; i++) {
            for (int j = 0, jlen = mineField[i].length - 1; j <= jlen; j++) {
                //checkleft, checkright,check up, check down
                int mineCount = 0;
                if (!mineField[i][j].equals("*")) {
                    //left
                    if (j > 0) {
                        if (mineField[i][j - 1].equals("*")) {
                            mineCount++;
                        }

                    }
                    //check right
                    if (j < jlen) {
                        if (mineField[i][j + 1].equals("*")) {
                            mineCount++;
                        }
                    }
                    //check top
                    if (i > 0) {
                        if (mineField[i - 1][j].equals("*")) {
                            mineCount++;
                        }
                        //check topleft
                        if (j > 0) {
                            if (mineField[i - 1][j - 1].equals("*")) {
                                mineCount++;
                            }
                        }
                        //check topright
                        if (j < jlen) {
                            if (mineField[i - 1][j + 1].equals("*")) {
                                mineCount++;
                            }
                        }
                    }
                    //check bottom
                    if (i < len) {
                        if (mineField[i + 1][j].equals("*")) {
                            mineCount++;
                        }
                        //check bottom left
                        if (j > 0) {
                            if (mineField[i + 1][j - 1].equals("*")) {
                                mineCount++;
                            }
                        }
                        //check bottom right
                        if (j < jlen) {
                            if (mineField[i + 1][j + 1].equals("*")) {
                                mineCount++;
                            }
                        }

                    }
                    mineField[i][j] = "" + mineCount;
                }
            }
        }
    }

    public static void main(String[] args) {
        String rawInput = "4 4\n"
                + "*...\n"
                + "....\n"
                + ".*..\n"
                + "....\n"
                + "3 5\n"
                + "**...\n"
                + ".....\n"
                + ".*...\n"
                + "4 4\n"
                + "*...\n"
                + "....\n"
                + ".*..\n"
                + "....\n"
                + "0 0";
        List<String[][]> mineFields = createMineFields(rawInput);
        StringBuilder sb = new StringBuilder();
        for (String[][] field : mineFields) {
            for (int i = 0, l = field.length; i < l; i++) {
                for (int j = 0, len = field[0].length; j < len; j++) {
                    sb.append(field[i][j]);
                }
                sb.append("\n");
            }
            sb.append("\n\n");
        }
        System.out.println(sb.toString());
        System.out.println();
        StringBuilder sb2 = new StringBuilder();
        for(int in=0,len=mineFields.size();in<len;in++) {
            String[][] field = mineFields.get(in);
            detectMineCount(field);
            int count=in+1;
            String header="Field #"+count+":";
            sb2.append(header);
            sb2.append("\n");
            for (int i = 0, l = field.length; i < l; i++) {
                for (int j = 0;j <field[0].length; j++) {
                    sb2.append(field[i][j]);
                }
                sb2.append("\n");
            }
            sb2.append("\n\n");
        }
        System.out.println(sb2.toString());

    }
}
