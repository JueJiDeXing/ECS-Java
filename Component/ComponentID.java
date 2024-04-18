package com.jjdx.ecosystem.Component;

import java.util.*;

import com.jjdx.ecosystem.Util.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 组件ID
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComponentID extends ID {
    private int id = 0;

    public ComponentID getAndIncrement() {
        return new ComponentID(id++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentID that = (ComponentID) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ComponentID(" + id + ")";
    }
}
