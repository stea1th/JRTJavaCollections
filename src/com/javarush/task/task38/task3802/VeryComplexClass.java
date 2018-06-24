package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/


import java.nio.file.Files;
import java.nio.file.Paths;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        Files.readAllLines(Paths.get("a:/"));
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();

    }
}
