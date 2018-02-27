/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egimaben.skiena.starter;

import java.util.Scanner;

/**
 *
 * @author egimaben
 */
public class Interpreter {

    static int counter = -1;
    static Integer[] registers = new Integer[10];
    static String[] ramwords = new String[1000];

    private static boolean isNan(String num) {
        try {
            Integer.parseInt(num);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static int reduce() {
        int steps = 0;
        int count=0;
        for (int i = 0; i <= counter; i++) {
            
            String ramword = ramwords[i];
            int instruction = Integer.parseInt(Character.toString(ramword.charAt(0)));
            int d = Integer.parseInt(Character.toString(ramword.charAt(1)));
            int n = Integer.parseInt(Character.toString(ramword.charAt(2)));
            switch (instruction) {
                case 0:
                    if (registers[n] != 0) {
                        steps++;
                        i=registers[d]-1;
                    }
                    break;
                case 1:
                    steps++;
                    break;
                case 2:
                    registers[d] = n;
                    steps++;
                    break;
                case 3:
                    registers[d] = (registers[d] + n)%1000;
                    steps += 3;
                    break;
                case 4:
                    registers[d] = (registers[d] * n)%1000;
                    steps += 3;
                    break;
                case 5:
                    registers[d] = registers[n];
                    steps += 2;
                    break;
                case 6:
                    registers[d] = (registers[d] + registers[n])%1000;
                    steps += 4;
                    break;
                case 7:
                    registers[d] = (registers[d] * registers[n])%1000;
                    steps += 4;
                    break;
                case 8:
                    registers[d] = Integer.parseInt(ramwords[registers[n]]);
                    steps += 3;
                    break;
                case 9:
                    registers[d] = Integer.parseInt(ramwords[registers[n]]);
                    steps += 3;
                    break;
                default:
                    break;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;

        }

        while (true) {
            String input = sc.nextLine();
            if (isNan(input)) {
                System.out.println("Invalid input...");
            } //case of a halt
            else if ("100".equals(input)) {
                System.out.println("reduction step...");
                int steps = reduce();
                steps++;
                System.out.println("Number of steps=" + steps);
                break;
            } else {
                ramwords[++counter] = input;
            }
        }
    }
}
