package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args)throws IOException {
        byte[] buffer = new byte[args[2].length()];
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")){
            randomAccessFile.seek(Long.parseLong(args[1]));
            randomAccessFile.read(buffer, 0, buffer.length);
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write((""+(new String(buffer).equals(args[2]))).getBytes());
        }
    }
}
