package com.jjdx.ecosystem.Event;

import lombok.Data;
/**
 信息存储器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
@Data
public class EventStaging<T> {
    private T data = null;

    public boolean isHaveData() {
        return data != null;
    }

    public void clear() {
        data = null;
    }

}
