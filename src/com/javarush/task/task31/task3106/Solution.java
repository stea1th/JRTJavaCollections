package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        /*Path destination = Paths.get(args[0]);
        Path zipFile =Paths.get(args[1].substring(0, args[1].lastIndexOf( ".")));
        byte[] buffer = new byte[1024];

        Queue<Path> queue = Arrays.stream(args)
                .skip(1)
                .sorted(Comparator.naturalOrder())
                .map(Paths::get)
                .collect(Collectors.toCollection(ArrayDeque::new));
        //queue.forEach(System.out::println);
        try(FileOutputStream fileOutputStream = new FileOutputStream(zipFile.toString())){
            while(!queue.isEmpty()){
                try(FileInputStream fileInputStream = new FileInputStream(queue.poll().toString())){
                    while((fileInputStream.read(buffer)>0)){
                        fileOutputStream.write(buffer, 0, buffer.length);
                    }
                }
            }
        }

        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile.toString()))){
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry!=null){
                Files.copy(zipInputStream, destination, StandardCopyOption.REPLACE_EXISTING);
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }

        }*/
        Vector files = new Vector();
        String[] x = Arrays.stream(args)
                .skip(1)
                .sorted()
                .toArray(String[]::new);

        IntStream.range(0, x.length)
                .forEach((i) -> {
                    try {
                        files.addElement(new FileInputStream(x[i]));
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    }
                });

        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(files.elements()));
             FileOutputStream fileOutputStream = new FileOutputStream(args[0])) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                copyData(zipInputStream, fileOutputStream);
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
