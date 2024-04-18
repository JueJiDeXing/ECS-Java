package com.jjdx.ecosystem.Util;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Util.Swaper;

import java.util.*;

/**
 池, 存储[组件|系统], 有添加删除接口
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Pool<T> {
    public LinkedList<T> list = new LinkedList<>();

    public void add(T e) {
        list.add(e);
    }

    public void remove(T e) {
        int idx = list.indexOf(e);
        if (idx == -1) return;
        Swaper.swap(list, idx, list.size() - 1);
        list.removeLast();
    }
}
