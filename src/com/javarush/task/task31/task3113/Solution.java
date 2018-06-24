package com.javarush.task.task31.task3113;

import java.io.File;
import java.io.IOException;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;


/* 
Что внутри папки?
*/
public class Solution {
    private long countAllFiles = 0;
    private long countAllDirs = 0;
    private long commonSize = 0;

    public static void main(String[] args) throws IOException {
        Path directory = Paths.get(new Scanner(System.in).nextLine());
        if(!Files.isDirectory(directory)){
            System.out.println(directory+" - не папка");
        }else{
            new Solution().printIt(directory);
        }
    }

    public void getInfo(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if(Files.isDirectory(path)){
                    countAllDirs++;
                    getInfo(path);
                }else if(Files.isRegularFile(path)){
                    countAllFiles++;
                    commonSize+=Files.readAllBytes(path).length;
                }
            }
        }

        /*countAllDirs =  Files.walk(directory).filter(Files::isDirectory).count();
        countAllFiles =  Files.walk(directory).filter(Files::isRegularFile).count();
        commonSize =  Files.walk(directory).filter(Files::isRegularFile).map(Path::toFile).mapToLong(Files::readAllBytes).sum();*/
    }

    public void printIt(Path directory) throws IOException {
        getInfo(directory);
        System.out.printf("Всего папок - %d\n" +
                "Всего файлов - %d\n" +
                "Общий размер - %d\n", countAllDirs, countAllFiles, commonSize);
    }
}
