package com.javarush.task.task38.task3810;


public @interface Revision {
    int revision();
    String comment() default "";
    Author[] authors() default {};
    Date date();
}
