package com.jjdx.ecosystem.Util;

import java.util.*;

/**
 List交换器(懒得每次都写交换了)
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */
public class Swaper {
    public static <T> void swap(List<T> list, int i, int j) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }
}
