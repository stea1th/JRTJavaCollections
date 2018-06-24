package com.javarush.task.task38.task3804;

public class FactoryClass {

    public static Throwable getException(Enum e){
        if(e == null){
            return new IllegalArgumentException();
        }
        String string = e.toString().toLowerCase().replace("_", " ");
        string = string.replaceFirst(string.substring(0, 1), string.substring(0,1).toUpperCase());
        switch(e.getDeclaringClass().getSimpleName()){
            case "ExceptionApplicationMessage" :
                return new Exception(string);
            case "ExceptionDBMessage" :
                return new RuntimeException(string);
            case "ExceptionUserMessage" :
                return new Error(string);
            default :
                return new IllegalArgumentException();
        }
    }
}
