package com.javarush.task.task39.task3913;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LogParser logParser = new LogParser(Paths.get("d:/logs/"));
        logParser.execute("get date for event = \"LOGIN\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"").forEach(System.out::println);
        //logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"11.12.2012 0:00:00\" and \"03.01.2016 23:59:59\"").forEach(System.out::println);
        /*logParser.getUniqueIPs(null, null).forEach(System.out::println);
        System.out.println("-------------------------------");
        logParser.getUniqueIPs(null, new SimpleDateFormat("d.M.y H:m:s").parse("03.03.2020 11:38:21")).forEach(System.out::println);
        System.out.println("-------------------------------");
        logParser.getIPsForUser("Amigo", null, null).forEach(System.out::println);
        System.out.println("-------------------------------");
        logParser.getIPsForStatus(Status.FAILED, null, null).forEach(System.out::println);
        System.out.println("-------------------------------");
        logParser.getIPsForEvent(Event.DOWNLOAD_PLUGIN, null, null).forEach(System.out::println);
        System.out.println("-------------------------------");
        logParser.getUniqueIPs(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2013"), new SimpleDateFormat("dd.MM.yyyy").parse("03.03.2020")).forEach(System.out::println);
        System.out.println("-------------------------------");*/

        //logParser.getAllLogsAsStrings().forEach(System.out::println);
        //System.out.println(logParser.getNumberOfUniqueIPs(null, new SimpleDateFormat("dd.MM.yyyy").parse("03.03.2020")));
        //Log log = new Log("123.23.45.2", "Vania",new SimpleDateFormat("d.M.y H:m:s").parse("03.03.2020 11:38:21"), Event.LOGIN, "", Status.FAILED);
        //LogParser.parseThisQuery(log, "getDate");
    }
}