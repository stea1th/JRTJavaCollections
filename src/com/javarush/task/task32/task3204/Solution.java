package com.javarush.task.task32.task3204;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args)throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()throws IOException {
        byte[] buffer = new byte[1024];
        try(InputStream inputStream = new ByteArrayInputStream(getResult().getBytes());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            while(inputStream.read(buffer)>0){
                byteArrayOutputStream.write(buffer, 0, buffer.length);
            }
            return byteArrayOutputStream;
        }
    }

    private static String getResult(){

        StringBuilder builder = new StringBuilder();
        while(true) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        builder.append((char) (int) (Math.random() * 26 + 97));
                        count++;
                        break;
                    case 1:
                        builder.append((char) (int) (Math.random() * 26 + 65));
                        count += 10;
                        break;
                    case 2:
                        builder.append((char) (int) (Math.random() * 10 + 48));
                        count += 100;
                        break;
                }
            }
            if(count%10!=0&&(count/10)%10!=0&&count<700&&count>100)
                break;
            else
                builder.delete(0, builder.length());
        }
        return builder.toString();
    }
}