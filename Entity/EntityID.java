package com.jjdx.ecosystem.Entity;

import java.util.*;

import com.jjdx.ecosystem.Util.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 实体ID,实体对象只由ID表示,其属性挂载在Component,行为挂载在System
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntityID extends ID {
    int id = 0;

    @Override
    public String toString() {
        return "EntityID" +
                "(" + id +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityID entityID = (EntityID) o;
        return id == entityID.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public EntityID getAndIncrement() {
        return new EntityID(id++);
    }
}
