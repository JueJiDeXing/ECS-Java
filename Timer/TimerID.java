package com.jjdx.ecosystem.Timer;

import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.Util.ID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 时间任务ID
 */
@NoArgsConstructor
@AllArgsConstructor
public class TimerID extends ID {

    int id = 0;

    public TimerID getAndIncrement() {
        return new TimerID(id++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerID that = (TimerID) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TimerID(" + id + ")";
    }
}
