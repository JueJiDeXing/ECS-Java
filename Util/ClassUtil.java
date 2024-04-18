package com.jjdx.ecosystem.Util;

import com.jjdx.ecosystem.System.StartSystems.StartSystemManger;
import com.jjdx.ecosystem.System.StartUpSystem;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 类管理
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class ClassUtil {
    /**
     扫描继承了T的类
     */
    public static <T, V> List<V> scan(Class<T> page, Class<V> c) {
        List<V> sysList = new ArrayList<>();
        try {
            String packageName = page.getPackage().getName();
            List<Class<?>> classes = ClassUtil.getClasses(packageName);
            for (Class<?> clazz : classes) {
                if (Modifier.isAbstract(clazz.getModifiers())) {
                    continue;
                }
                if (c.isAssignableFrom(clazz)) {
                    V sys = (V) clazz.newInstance();
                    sysList.add(sys);
                }
            }
        } catch (Exception e) {

        }
        return sysList;
    }

    /**
     获取包下的所有类
     */
    public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) return classes;

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
