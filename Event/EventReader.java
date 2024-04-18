package com.jjdx.ecosystem.Event;

import lombok.NoArgsConstructor;

import java.util.*;

/**
 读取信息
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
public class EventReader<T> {
    EventStaging<T> eventStaging  ;

    public EventReader(EventStaging<T> eventStaging) {
        this.eventStaging = eventStaging;
    }

    public void setEventStaging(EventStaging<T> eventStaging) {
        this.eventStaging = eventStaging;
    }

    public boolean isHave() {
        return eventStaging.isHaveData();
    }

    public T read() {
        return eventStaging.getData();
    }
}
