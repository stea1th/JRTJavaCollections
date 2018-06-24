package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("http://toogle.com/files/rules.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path  downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        try(InputStream input = url.openStream()) {
            String[] attributes = Paths.get(url.getPath()).getFileName().toString().split("\\.");
            Path tempFile = Files.createTempFile(attributes[0], "." + attributes[1]);
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            if (!Files.exists(downloadDirectory)) {
                Files.createDirectory(downloadDirectory);
            }
            Files.move(tempFile, downloadDirectory.resolve(Paths.get(url.getPath()).getFileName()), StandardCopyOption.REPLACE_EXISTING);
        }
        return downloadDirectory.resolve(Paths.get(url.getPath()).getFileName());
    }
}
