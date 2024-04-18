package com.jjdx.ecosystem.Timer;

import lombok.Getter;

import java.util.*;

/**
 定时任务管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class TimerTasker {

    TimerID id = new TimerID(0);
    @Getter
    private static final TimerTasker instance = new TimerTasker();

    HashMap<TimerID, Thread> idToThread = new HashMap<>();

    /**
     添加任务

     @param task 要执行任务
     */
    public TimerID schedule(Runnable task) {
        Thread thread = new Thread(task);
        thread.setName("TimerTasker-" + id.curId);
        thread.start();
        return addThread(thread);
    }

    /**
     存储任务
     */
    private TimerID addThread(Thread thread) {
        TimerID ass = id.getAndIncrement();
        idToThread.put(ass, thread);
        return ass;
    }

    /**
     移除任务(终止线程)
     */
    public void removeTimer(TimerID id) {
        Thread thread = idToThread.remove(id);
        thread.interrupt();
    }

    /**
     移除全部任务
     */
    public void removeAllTimer() {
        for (Thread thread : idToThread.values()) {
            thread.interrupt();
        }
        idToThread.clear();
    }
}
