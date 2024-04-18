package com.jjdx.ecosystem.Resource;

import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Util.IDGenerator;
import lombok.Getter;

/**
 resourceID生成器
 <br>
 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br>
 */
public class ResourceIDGenerator extends IDGenerator {
    private static final ResourceID curIdx = new ResourceID(0);
    @Getter
    private static final ResourceIDGenerator instance = new ResourceIDGenerator();

    private ResourceIDGenerator() {
    }

    @Override
    public ResourceID Gen() {
        return curIdx.getAndIncrement();
    }
}
