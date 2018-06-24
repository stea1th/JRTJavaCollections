package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {

        try(RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")){

            randomAccessFile.seek(randomAccessFile.length()< Long.parseLong(args[1])? randomAccessFile.length() : Long.parseLong(args[1]));


            randomAccessFile.write(args[2].getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
