package com.jjdx.ecosystem.Event;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 事件者,用于各模块之间传递信息
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
@Data
public class Eventer {
    private static final Eventer eventer = new Eventer();

    public static Eventer getInstance() {
        return eventer;
    }

    public List<EventFunc> eventFuncQueue = new ArrayList<>();

    static HashMap<Class, EventReader> readerMap = new HashMap<>();
    static HashMap<Class, EventWriter> writerMap = new HashMap<>();

    /**
     获取事件写入器

     @param <T>事件类型
     */
    public static <T> EventWriter writer(T c) {
        Class<?> clazz = c.getClass();
        if (writerMap.containsKey(clazz)) return writerMap.get(clazz);//该类事件写入器已存在
        EventStaging eventStaging = getEventStaging(clazz, readerMap);
        EventWriter eventWriter = new EventWriter<>(eventStaging);
        writerMap.put(clazz, eventWriter);
        return eventWriter;
    }

    private static EventStaging getEventStaging(Class<?> clazz,
                                                HashMap<Class, ? extends EventOperator> map) {
        EventStaging eventStaging;
        if (map.containsKey(clazz)) {
            eventStaging = map.get(clazz).eventStaging;//获取过读取器
        } else {
            eventStaging = new EventStaging<>();
        }
        return eventStaging;
    }

    /**
     获取事件读取器

     @param <T> 事件类型
     */
    public static <T> EventReader reader(T c) {
        Class<?> clazz = c.getClass();
        if (readerMap.containsKey(clazz)) return readerMap.get(clazz);
        EventStaging eventStaging = getEventStaging(clazz, writerMap);
        EventReader eventReader = new EventReader<>(eventStaging);
        readerMap.put(clazz, eventReader);
        return eventReader;
    }

    /**
     执行该帧事件
     */
    public void applyEvents() {
        for (EventFunc func : eventFuncQueue) {
            func.apply();
        }
        eventFuncQueue.clear();
    }


    @Override
    public String toString() {
        return "Eventer{" + "addEventFuncs=" + eventFuncQueue + '}';
    }
}
