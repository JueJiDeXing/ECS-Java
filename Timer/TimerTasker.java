package com.jjdx.ecosystem.Timer;

import lombok.Getter;

import java.util.*;

/**
 定时任务管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class TimerTasker {

    TimerID id = new TimerID();
    @Getter
    private static final TimerTasker instance = new TimerTasker();

    HashMap<TimerID, Timer> idToTimer = new HashMap<>();
    HashMap<Timer, TimerID> timerToId = new HashMap<>();

    /**
     添加任务

     @param task 任务
     */
    public TimerID schedule(TimerTask task, long delay, long period) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, period);
        return addTimer(timer);
    }

    /**
     存储任务
     */
    private TimerID addTimer(Timer timer) {
        idToTimer.put(id, timer);
        timerToId.put(timer, id);
        return id.getAndIncrement();
    }


    public void removeTimer(TimerID id) {
        Timer timer = idToTimer.remove(id);
        timerToId.remove(timer);
        timer.cancel();
    }

    public void removeAllTimer() {
        for (Timer timer : timerToId.keySet()) timer.cancel();
        idToTimer.clear();
        timerToId.clear();
    }
}
