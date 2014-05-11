package com.treestompz.cscomp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<Integer, String> map = new HashMap<Integer, String>();
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    public static void main(String[] args) {
        map.put(0, "#");
        for(int i = 0; i <alphabet.length; i++) {
            map.put(i + 1, String.valueOf(alphabet[i]));
        }
        // System.out.println(map.toString());
        String output = "";
        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toLowerCase().toCharArray();
        for(int j = 0; j < input.length; j++) {
            String letter = String.valueOf(input[j]);
            output += convert(letter);
        }
        System.out.println(output.toUpperCase());
    }
    
    public static String convert(String letter) {
        int val = new String(alphabet).indexOf(letter) + 1;
        // System.out.println("Letter value:" + val);
        // A-E : Multiply letter value by 2
        if(val <= 5) {
            return map.get(val + val * 2);
        }
        // F-J : Divide letter value by 3. Multiply the integer remainder by 5.
        else if(val <= 10) {
            return map.get(val + val % 3 * 5);
        }
        // K-O : Divide the letter value by 4. Multiply the integer part of the quotient by 8.
        else if(val <= 15) {
            int newVal = getNewVal(val, val / 4 * 8);
            return map.get(newVal);
        }
        // P-T : Multiply the sum of the digits of the letter value by 10.
        else if(val <= 20) {
            // 17 -> 1 + 7 = 8 * 10 = answer
            char[] dos = String.valueOf(val).toCharArray();
            int sum = Integer.parseInt(String.valueOf(dos[0])) + Integer.parseInt(String.valueOf(dos[1]));
            //System.out.println("Sum: " +sum);
            int newVal = getNewVal(val, sum * 10);
            return map.get(newVal);
        }
        // U-Z : Find the largest integer factor if its letter value less than that of the value itself. Multiply it by 12.
        else {
            // val = 26
            int largestFactor = 0;
            for(int i = 2; i < val; i++) {
                if(val % i == 0) {
                    largestFactor = val / i;
                    break;
                }
            }
            int newVal = getNewVal(val, largestFactor * 12);
            return map.get(newVal);
        } 
    }
    
    public static int getNewVal(int letterVal, int jumpAheadBy) {
        if(letterVal + jumpAheadBy <= 26) {
            return letterVal;
        }
        while(jumpAheadBy > 0) {
            if(letterVal == 26) {
                letterVal = 0;
            } else {
                letterVal++;
            }
            jumpAheadBy--;
            // System.out.println("Jump: " + jumpAheadBy + "Letter val: " + letterVal);
        }
        return letterVal;
    }

}
