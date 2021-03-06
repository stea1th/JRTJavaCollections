package com.javarush.task.task32.task3206;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ItemInvocationHandler implements InvocationHandler {

    Class[] item;

    public ItemInvocationHandler(Class[] item) {
        this.item = item;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for(Class cl : item){
            return method.invoke(cl, args);
        }
        return null;
    }
}