package com.javarush.task.task36.task3606;



import java.io.File;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Path x = Paths.get(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1)).relativize(Paths.get(packageName.substring(1)));
        String classPath = x.toString().replace("\\", ".");
        File dir = Paths.get(packageName.substring(1)).toFile();
        for(File file : Objects.requireNonNull(dir.listFiles())){
            if(file.getName().endsWith(".class")){
                hiddenClasses.add(classLoader.loadClass(classPath+"."+file.getName().substring(0, file.getName().length()-6)));
            }
        }
    }


    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for(Class c : hiddenClasses){
                if(c.getSimpleName().toLowerCase().contains(key.toLowerCase())) {
                    Constructor constructor = c.getDeclaredConstructors()[0];
                    constructor.setAccessible(true);
                    return ((HiddenClass) constructor.newInstance());
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

