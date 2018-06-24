package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    private List<File> smallFiles = new ArrayList<>();
    private File directory;

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.directory = Paths.get(args[0]).toFile();
        File resultFileAbsolutePath = new File(args[1]);
        File tempFile = new File(resultFileAbsolutePath.getParent()+File.separator+"allFilesContent.txt");

        if(FileUtils.isExist(resultFileAbsolutePath)) {
            FileUtils.renameFile(resultFileAbsolutePath, tempFile);
        }

        try {
            solution.writeToFile(tempFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(File contentFile)throws Exception{
            FileInputStream input;
        try(FileOutputStream output = new FileOutputStream(contentFile, true)) {
            getAllFiles(directory);
            sort();
            for (File file : smallFiles) {
                    input = new FileInputStream(file);
                    byte[] buffer = new byte[input.available()];
                    input.read(buffer, 0, buffer.length);
                    output.write(buffer, 0, buffer.length);
                    output.write(System.getProperty("line.separator").getBytes());
                    input.close();
            }
        }
    }

    public void getAllFiles(File directory){
        if(directory.isDirectory()) {
            List<File> fileList = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
            for(File file : fileList){
                if(file.isDirectory()){
                    getAllFiles(file);
                }else{

                    if(file.length()<=50){
                        smallFiles.add(file);
                    }
                }
            }
        }
    }

    public void sort(){
        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        smallFiles.sort(comparator);
    }
}
