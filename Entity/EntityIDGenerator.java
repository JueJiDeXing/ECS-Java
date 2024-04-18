package com.jjdx.ecosystem.Entity;

import com.jjdx.ecosystem.Util.IDGenerator;
import lombok.Getter;

/**
 实体对象(ID)生成器
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */

public class EntityIDGenerator extends IDGenerator {

    private static final EntityID curID = new EntityID(0);
    @Getter
    private static final EntityIDGenerator instance = new EntityIDGenerator();

    private EntityIDGenerator() {
    }

    public EntityID Gen() {
        return curID.getAndIncrement();
    }

}
