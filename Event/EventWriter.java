package com.jjdx.ecosystem.Event;

/**
 写入信息
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
public class EventWriter<T> extends EventOperator<T> {

    public EventWriter(EventStaging<T> eventStaging) {
        this.eventStaging = eventStaging;
    }

    /**
     写入信息到eventStaging,作为事件存入eventFuncQueue
     */
    public void write(T t) {
        Eventer.getInstance().eventFuncQueue.add(() -> eventStaging.setData(t)); // 向添加消息推入一个写入函数
    }

    /**
     写入信息到eventStaging,立即写入
     */
    public void writeImmediate(T t) {
        eventStaging.setData(t);
    }
}
