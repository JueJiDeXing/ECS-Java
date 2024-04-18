package com.jjdx.ecosystem.Component;

import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Util.SparseSet;
import com.jjdx.ecosystem.Util.Pool;

/**
 组件信息,每类组件都映射一个组件信息
 组件信息关联到拥有该类组件的entity列表,以及该类组件的对象池
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/17 <br> */
public class ComponentInfo {
    /**
     组件池,管理一类组件的添加和删除
     */
    public Pool<Component> pool = new Pool<>();
    /**
     实体对象集合
     */
    public SparseSet entitySet = new SparseSet();

    /**
     添加一个实体
     */
    void addEntity(EntityID entityID) {
        entitySet.add(entityID.getId());
    }

    /**
     删除一个实体
     */
    void removeEntity(EntityID entityID) {
        entitySet.remove(entityID.getId());
    }
}
