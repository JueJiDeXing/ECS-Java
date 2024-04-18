package com.jjdx.ecosystem.Event;

/**
 写入信息
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
public class EventWriter<T> {
      EventStaging<T> eventStaging;

    public EventWriter(EventStaging<T> eventStaging) {
        this.eventStaging = eventStaging;
    }

    public void setEventStaging(EventStaging<T> eventStaging) {
        this.eventStaging = eventStaging;
    }

    public void write(T t) {
        Eventer.getInstance().eventFuncList.add(() -> eventStaging.setData(t)); // 向添加消息推入一个写入函数
    }

    public void writeImmediate(T t) {
        eventStaging.setData(t);
    }
}
