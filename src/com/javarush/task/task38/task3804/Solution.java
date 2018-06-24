package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return FactoryClass.class;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        FactoryClass.getException(ExceptionApplicationMessage.SOCKET_IS_CLOSED);
        FactoryClass.getException(ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS);
        FactoryClass.getException(MyException.NOT_AT_HOME);



    }
}