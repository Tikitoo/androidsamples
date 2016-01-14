package me.tikitoo.androiddemo.utils;

import me.tikitoo.androiddemo.MainActivity;

public class ClassUtils {
    public static void main(String[] args) {
        String name = getName(MainActivity.class);
//        getName(MainActivity);

    }
    public static String getName(Object obj) {
        return obj.getClass().getName();
    }
    public static String getName(Class tClass) {
        return tClass.getName();
    }
}
