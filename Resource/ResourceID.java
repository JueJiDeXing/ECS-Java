package com.jjdx.ecosystem.Resource;

import com.jjdx.ecosystem.Util.ID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 资源ID
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResourceID extends ID {
    int id = 0;

    public ResourceID getAndIncrement() {
        return new ResourceID(id++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceID that = (ResourceID) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ResourceID(" + id + ")";
    }
}
