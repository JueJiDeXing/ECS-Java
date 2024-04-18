package com.jjdx.ecosystem.Component;

import com.jjdx.ecosystem.Util.IDGenerator;
import lombok.Getter;

/**
 组件ID生成器
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */
public class ComponentIDGenerator extends IDGenerator {
    private static final ComponentID curIdx = new ComponentID(0);
    @Getter
    private static final ComponentIDGenerator instance = new ComponentIDGenerator();

    private ComponentIDGenerator() {
    }

    @Override
    public ComponentID Gen() {
        return curIdx.getAndIncrement();
    }
}
