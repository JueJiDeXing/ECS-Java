package com.jjdx.ecosystem.Event;


import lombok.Data;

import java.util.*;
import java.util.ArrayList;

/**
 事件者,用于各模块之间传递信息
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
@Data
public class Eventer {
    private static final Eventer eventer = new Eventer();
    /**
     线程是否需要关闭,模块可能分布在各个线程中, 该字段用于主线程关闭后通知线程退出
     */
    private boolean shutdown = false;

    public static Eventer getInstance() {
        return eventer;
    }

    public List<EventFunc> eventFuncList = new ArrayList<>();

    static HashMap<Class, EventReader> readerMap = new HashMap<>();
    static HashMap<Class, EventWriter> writerMap = new HashMap<>();

    /**
     获取事件读取器

     @param <T> 事件类型
     */
    public static <T> EventReader<T> reader(T c) {
        if (readerMap.containsKey(c.getClass())) return readerMap.get(c.getClass());
        EventStaging<T> eventStaging;
        if (writerMap.containsKey(c.getClass())) {
            eventStaging = writerMap.get(c.getClass()).eventStaging;
        } else {
            eventStaging = new EventStaging<>();
        }

        EventReader<T> eventReader = new EventReader<>(eventStaging);
        readerMap.put(c.getClass(), eventReader);
        return eventReader;
    }

    /**
     获取事件写入器

     @param <T>事件类型
     */
    public static <T> EventWriter<T> writer(T c) {
        if (writerMap.containsKey(c.getClass())) return writerMap.get(c.getClass());
        EventStaging<T> eventStaging;
        if (readerMap.containsKey(c.getClass())) {
            eventStaging = readerMap.get(c.getClass()).eventStaging;
        } else {
            eventStaging = new EventStaging<>();
        }
        EventWriter<T> eventWriter = new EventWriter<>(eventStaging);
        writerMap.put(c.getClass(), eventWriter);
        return eventWriter;
    }

    public void addAllEvents() {
        for (EventFunc func : eventFuncList) {
            func.apply();
        }
        eventFuncList.clear();
    }


    @Override
    public String toString() {
        return "Eventer{" + "addEventFuncs=" + eventFuncList + '}';
    }
}
