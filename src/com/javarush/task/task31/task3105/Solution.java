package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws Exception {

       Path tempZip = Files.createTempFile(null, ".zip");
       Path source = Paths.get("new\\"+Paths.get(args[0]).getFileName());
       Path zipFile = Paths.get(args[1]);

       try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(tempZip));
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))){

           ZipEntry zipEntry = zipInputStream.getNextEntry();

           while(zipEntry!=null){
               if(!zipEntry.getName().equals(source.toString())) {
                   zipOutputStream.putNextEntry(new ZipEntry(zipEntry.getName()));
                   copyData(zipInputStream, zipOutputStream);
                   zipOutputStream.closeEntry();
                   zipInputStream.closeEntry();
                   zipEntry = zipInputStream.getNextEntry();
               }else{
                   break;
               }
           }
           zipOutputStream.putNextEntry(new ZipEntry(source.toString()));
           Files.copy(Paths.get(args[0]), zipOutputStream);
           zipOutputStream.closeEntry();
       }
       Files.move(tempZip, zipFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }


}
