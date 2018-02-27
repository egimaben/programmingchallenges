/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egimaben.skiena.starter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author egimaben
 */
public class GraphicalEditor {

    static String[][] image = null;

    private static boolean validateCmd(String input) {
        String[] inputArr = input.split(" ");
        boolean result = true;
        int length = inputArr.length;
        String cmd = inputArr[0];
        switch (length) {
            case 1:
                if (!("X".equals(cmd) || "C".equals(cmd))) {
                    result = false;
                }
                ;
                break;
            case 2:
                if (!"S".equals(cmd)) {
                    result = false;
                } else if (!inputArr[1].matches(".*\\.bmp")) {
                    result = false;
                }
                ;
                break;
            case 3:

                if (!"I".equals(cmd)) {
                    result = false;
                } else if (!validCoordinates(inputArr[1], inputArr[2])) {
                    result = false;
                }
                break;
            case 4:
                if (!"L".equals(cmd) && !"F".equals(cmd)) {
                    result = false;
                } else if (!validCoordinates(inputArr[1], inputArr[2])) {
                    result = false;
                } else if (!isValidColor(inputArr[3])) {
                    result = false;
                }
                break;
            case 5:
                if (!"V".equals(cmd) && !"H".equals(cmd)) {
                    result = false;
                } else if ("V".equals(cmd) && (!validCoordinates(inputArr[1], inputArr[2]) || !validCoordinates("1", inputArr[3]))) {
                    result = false;
                } else if ("H".equals(cmd) && (!validCoordinates(inputArr[1], "1") || !validCoordinates(inputArr[2], inputArr[3]))) {
                    result = false;
                } else if (!isValidColor(inputArr[4])) {
                    result = false;
                }
                break;
            case 6:
                if (!"K".equals(cmd)) {
                    result = false;
                } else if (!validCoordinates(inputArr[1], inputArr[2]) || !validCoordinates(inputArr[3], inputArr[4])) {
                    result = false;
                } else if (!isValidColor(inputArr[5])) {
                    result = false;
                }
                break;
            default:
                break;

        }

        return result;
    }

    private static boolean isValidColor(String s) {
        return isUpper(s);
    }

    private static boolean isUpper(String s) {
        return Character.isUpperCase(s.codePointAt(0));
    }

    private static boolean validCoordinates(String m, String n) {
        boolean valid = true;
        if (isNan(m) || isNan(n)) {
            valid = false;
        } else if (Integer.parseInt(m) < 1 || Integer.parseInt(n) > 250) {
            valid = false;
        }

        return valid;
    }

    private static boolean isNan(String num) {
        try {
            Integer.parseInt(num);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static void fill(int xCoord, int yCoord, String color) {

        String origCol = image[yCoord][xCoord];

        int maxRow = image.length - 1;
        int maxCol = image[0].length - 1;
        Queue<Integer[]> toColonize = new LinkedList<>();

        //first enqueue initial pixel to colonize
        toColonize.add(new Integer[]{yCoord, xCoord});
        while (toColonize.peek() != null) {
            Integer[] currPixel=toColonize.poll();
            int n=currPixel[0];
            int m=currPixel[1];
            //colonize pixel, before colonizing neighbors
            image[n][m]=color;
            //check if n-1,m is colonizable
            if (n > 0 && image[n - 1][m].equals(origCol)) {
                toColonize.add(new Integer[]{n - 1, m});
            }
            //check if n+1,m is colonizable
            if (n < maxRow && image[n + 1][m].equals(origCol)) {
                toColonize.add(new Integer[]{n + 1, m});
            }
            //check if n,m-1 is colonizable
            if (m > 0 && image[n][m - 1].equals(origCol)) {
                toColonize.add(new Integer[]{n, m - 1});
            }
            //check if n,m+1 is colonizable
            if (m < maxCol && image[n][m + 1].equals(origCol)) {
                toColonize.add(new Integer[]{n, m + 1});
            }
        }
    }

    private static String processInput(String input) {
        String response = null;
        String[] inputArr = input.split(" ");
        int length = inputArr.length;
        String cmd = inputArr[0];
        switch (length) {
            case 1:
                if ("X".equals(cmd)) {
//                    System.out.println("Terminate the session");
                } else if ("C".equals(cmd)) {
//                    System.out.println("Clear the table by setting all pixels white (O). The size remains unchanged");
                    for (int i = 0; i < image.length; i++) {
                        for (int j = 0; j < image[i].length; j++) {
                            image[i][j] = "O";
                        }
                    }
                }

                break;
            case 2:
                String fileName = inputArr[1];
//                System.out.println("Write the file name in MSDOS 8.3 format followed by the contents: " + fileName);
                StringBuilder sb = new StringBuilder();
                sb.append(fileName);
                sb.append("\n");
                if (image != null) {
                    for (int i = 0; i < image.length; i++) {
                        for (int j = 0; j < image[i].length; j++) {
                            sb.append(image[i][j]);
                        }
                        sb.append("\n");
                    }
                    response = sb.toString();
                    System.out.println(response);
                } else {
//                    System.out.println("No image pixels found");
                }

                break;
            case 3:
                int m = Integer.parseInt(inputArr[1]);
                int n = Integer.parseInt(inputArr[2]);
//                System.out.println("creating a new " + m + "x" + n + " image with all pixels colored white O");
                image = new String[n][m];
                for (int i = 0; i < image.length; i++) {
                    for (int j = 0; j < image[i].length; j++) {
                        image[i][j] = "O";
                    }
                }
                break;
            case 4:
                int m1 = Integer.parseInt(inputArr[1]);
                int n1 = Integer.parseInt(inputArr[2]);
                String color1 = inputArr[3];
                if ("L".equals(cmd)) {
//                    System.out.println("Color pixel (" + m1 + "," + n1 + ") in color " + color1);
                    image[n1-1][m1-1] = color1;

                } else if ("F".equals(cmd)) {
//                    System.out.println("Fill the region R with color " + color1 + ", where R is defined as follows: Pixel (" + m1 + "," + n1 + ") "
//                            + "belongs to R. Any other pixel which is the same color as pixel(" + m1 + "," + n1 + ") and shares a common side "
//                            + "with any pixel in R also belongs to this region");
                    fill(n1-1, m1-1, color1);
                }
                break;
            case 5:
                int x = Integer.parseInt(inputArr[1]);
                int y = Integer.parseInt(inputArr[3]);

                String color2 = inputArr[4];
                if ("V".equals(cmd)) {
                    int y2 = Integer.parseInt(inputArr[2]);
//                    System.out.println("Draw a vertical segment of color (" + color2 + ") in column " + x + ", between the rows " + y2 + " and " + y + " inclusive");
                    for (int j = y2; j <= y; j++) {
                        image[j-1][x-1] = color2;
                    }
                } else if ("H".equals(cmd)) {
                    int x2 = Integer.parseInt(inputArr[2]);
//                    System.out.println("Draw a horizontal segment of color (" + color2 + ") in row " + y + ", between the columns " + x + " and " + x2 + " inclusive");
                    for (int j = x; j <= x2; j++) {
                        image[y-1][j-1] = color2;
                    }
                }
                break;
            case 6:
                String color3 = inputArr[5];
                int xx1 = Integer.parseInt(inputArr[1]);
                int yy1 = Integer.parseInt(inputArr[2]);
                int xx2 = Integer.parseInt(inputArr[3]);
                int yy2 = Integer.parseInt(inputArr[4]);
//                System.out.println("Draw a filled rectangle of color " + color3 + ", where (" + xx1 + "," + yy1 + ") is the upper left"
//                        + "corner and (" + xx2 + "," + yy2 + ") is the lower right corner");
                for (int i = yy1; i < yy2; i++) {
                    for (int j = xx1; j < xx2; j++) {
                        image[i-1][j-1] = color3;
                    }
                }
                break;
            default:
                break;

        }

        return response;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
//            System.out.println("Input a command:\n");
            String input = sc.nextLine();
            if (validateCmd(input)) {

                processInput(input);
                if ("X".equals(input)) {
                    break;
                }
            } else {
//                System.out.println("wrong input\n");
            }
        }

    }
}
