package com.jjdx.ecosystem.Timer;

import com.jjdx.ecosystem.Util.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 时间任务ID
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimerID extends ID {
   int curId = 0;


    public TimerID getAndIncrement() {
        return new TimerID(curId++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerID that = (TimerID) o;
        return curId == that.curId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(curId);
    }

    @Override
    public String toString() {
        return "TimerID(" + curId + ")";
    }
}
