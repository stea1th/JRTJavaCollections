package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    
    private final static Map<String, Integer> map = new LinkedHashMap<>();

    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toCharArray();
        String previousLetter = String.valueOf(chars[0]);
        int summe = map.get(previousLetter);
        if (chars.length > 1) {
            for (int i = 1; i < chars.length; i++) {
                String thisLetter = String.valueOf(chars[i]);
                summe+=map.get(thisLetter) <= map.get(previousLetter)? map.get(thisLetter) : map.get(thisLetter)-map.get(previousLetter)*2;
                previousLetter = thisLetter;
            }
        }
        return summe;
    }
}
