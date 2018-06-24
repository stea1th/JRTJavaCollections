package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new ArrayDeque<>();
        List<File>fileList = new ArrayList<>();
        queue.offer(new File(root));
        while(!queue.isEmpty()){
            File[] files = queue.poll().listFiles();
            if(files.length>0) {
                Arrays.stream(files)
                        .filter(File::isFile)
                        .forEach(fileList::add);
                Arrays.stream(files)
                        .filter(File::isDirectory)
                        .forEach(queue::offer);
            }
        }
        return fileList.stream()
                .map(File::toPath)
                .map(Path::toAbsolutePath)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            getFileTree("d:/archiv")
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
