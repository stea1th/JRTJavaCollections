package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        List<Integer> list = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        if(s!=null && s.length()>0){
            for(int i : s.chars().toArray()){
                if(list.contains(i)){
                    count=1;
                    list = new ArrayList<>();
                }else{
                    count++;
                }
                list.add(i);
                maxCount=maxCount<count? count : maxCount;
            }
        }
        return maxCount;
    }
}
