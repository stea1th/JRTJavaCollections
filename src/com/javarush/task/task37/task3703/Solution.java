package com.javarush.task.task37.task3703;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        /*new ConcurrentHashMap();
        new ConcurrentNavigableMap<>();
        new ConcurrentSkipListMap<>();*/

        return ConcurrentSkipListMap.class;
    }
}
