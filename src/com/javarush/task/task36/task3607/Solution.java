package com.javarush.task.task36.task3607;


import java.util.concurrent.DelayQueue;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws ClassNotFoundException {

        return Class.forName("java.util.concurrent.DelayQueue");
    }
}
