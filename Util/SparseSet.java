package com.jjdx.ecosystem.Util;

import java.util.*;

/**
 快速添加删除元素的稀疏集合,仅支持整数类型
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class SparseSet {
    LinkedList<Integer> list = new LinkedList<>();
    List<Integer[]> indexes = new ArrayList<>();// indexes[item] = list.indexOf(item)
    int Capacity = 100;// 默认每行100个元素

    /**
     获取元素
     */
    public Integer get(int item) {
        int r = item / Capacity, c = item % Capacity;
        if (r >= indexes.size()) return null;
        Integer idx = indexes.get(r)[c];
        return idx == null ? null : list.get(idx);
    }

    /**
     添加元素
     */
    public void add(int item) {
        int r = item / Capacity, c = item % Capacity;
        for (int i = indexes.size(); i <= r; i++) indexes.add(new Integer[Capacity]);//超出容量
        indexes.get(r)[c] = list.size();// 元素添加到list末尾, 在indexes中设置索引指向
        list.add(item);
    }

    /**
     删除元素
     */
    public void remove(int item) {
        int r = item / Capacity, c = item % Capacity;
        if (r >= indexes.size()) return;
        Integer idx = indexes.get(r)[c];
        if (idx == null) return;
        Swapper.swap(list, idx, list.size() - 1);//交换到末尾后删除
        list.pollLast();
        indexes.get(r)[c] = null;
    }
}
