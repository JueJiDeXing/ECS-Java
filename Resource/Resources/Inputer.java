package com.jjdx.ecosystem.Resource.Resources;

import com.jjdx.ecosystem.Event.EventWriter;
import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Timer.TimerID;
import com.jjdx.ecosystem.Timer.TimerTasker;

import java.io.IOException;
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
        timerID = TimerTasker.getInstance().schedule(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    if (System.in.available() > 0) {
                        eventWriter.write(sc.next());
                    } else if (timerID == null) {
                        break;
                    }
                } catch (IOException e) {
                    break;
                }
            }
        });
    }

    @Override
    public void destroy() {
        if (timerID != null) {
            TimerTasker.getInstance().removeTimer(timerID);
            timerID = null;
        }
    }
}
