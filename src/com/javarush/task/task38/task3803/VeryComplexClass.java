package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object ch = new Character('*');
        System.out.println((Byte)ch);
    }

    public void methodThrowsNullPointerException() throws ParseException {
        String x = null;
        new SimpleDateFormat("d.M.y").parse(x);

    }

    public static void main(String[] args) throws ParseException {
        //new VeryComplexClass().methodThrowsNullPointerException();
        new VeryComplexClass().methodThrowsClassCastException();

    }
}
