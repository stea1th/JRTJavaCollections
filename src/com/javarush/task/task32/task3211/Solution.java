package com.javarush.task.task32.task3211;

import com.sun.prism.PixelFormat;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        StringBuilder stringBuilder = new StringBuilder();
        messageDigest.update(byteArrayOutputStream.toByteArray());
        for(byte b : messageDigest.digest()){
            stringBuilder.append(String.format("%02x", b));
        }
        return MessageDigest.isEqual(md5.getBytes(), stringBuilder.toString().getBytes());
    }
}
