package com.jjdx.ecosystem.Resource.Resources;

import com.jjdx.ecosystem.Event.EventWriter;
import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Timer.TimerID;
import com.jjdx.ecosystem.Timer.TimerTasker;

import java.util.*;

/**
 控制台输入转发给Event
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class Inputer extends Resource {
    EventWriter<String> eventWriter;

    public Inputer(EventWriter<String> eventWriter) {
        this.eventWriter = eventWriter;
        addTask(eventWriter);
    }

    TimerID timerID;

    private void addTask(EventWriter<String> eventWriter) {
        timerID = TimerTasker.getInstance().schedule(new TimerTask() {
            final Scanner sc = new Scanner(System.in);

            @Override
            public void run() {
                if (sc.hasNext()) {
                    eventWriter.write(sc.next());
                }
            }
        }, 0, 100);
    }

    @Override
    public void destroy() {
        if (timerID != null) TimerTasker.getInstance().removeTimer(timerID);
    }
}
