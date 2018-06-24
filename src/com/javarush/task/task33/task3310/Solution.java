package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ids = new HashSet<>();
        for(String value : strings){
            ids.add(shortener.getId(value));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for(Long key : keys){
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> generateStrings = LongStream.range(0, elementsNumber)
                .boxed()
                .map(i->{
                    return Helper.generateRandomString();
                })
                .collect(Collectors.toSet());
        Shortener shortener = new Shortener(strategy);
        Date startId = new Date();//Calendar.getInstance().getTime();
        Set<Long> ids = getIds(shortener, generateStrings);
        Date finishId = new Date(); //Calendar.getInstance().getTime();
        Helper.printMessage((finishId.getTime()-startId.getTime())+"");
        Date startString = new Date(); //Calendar.getInstance().getTime();
        Set<String> strings = getStrings(shortener, ids);
        Date finishString = new Date(); //Calendar.getInstance().getTime();
        Helper.printMessage((finishString.getTime()-startString.getTime())+"");
        Helper.printMessage(generateStrings.size()==strings.size()? "Тест пройден." : "Тест не пройден.");


    }
}
