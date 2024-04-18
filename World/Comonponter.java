package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Component.ComponentIDGenerator;
import com.jjdx.ecosystem.Component.ComponentInfo;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Entity.EntityIDGenerator;
import lombok.Getter;

import java.util.HashMap;

/**
 组件管理器,管理实体和组件
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Comonponter {

    @Getter
    private static final Comonponter instance = new Comonponter();

    private Comonponter() {
    }



    /**
     组件id -> 组件对象
     */
    HashMap<ComponentID, Component> componentMap = new HashMap<>();
    /**
     组件类 -> 组件类信息 -> 该类组件池(所有该类组件对象) && 该类组件关联的实体对象集合
     */
    HashMap<Class<Component>, ComponentInfo> componentClassToInfo = new HashMap<>();
    /**
     entity对象 -> 该对象关联的Component列表
     */
    HashMap<EntityID, HashMap<ComponentID, Component>> entities = new HashMap<>();

    /**
     销毁实体
     */
    private void destroyEntity(EntityID entityID) {
        if (!entities.containsKey(entityID)) return;
        //实体关联组件列表
        HashMap<ComponentID, Component> idToComponent = entities.get(entityID);
        entities.remove(entityID);
        //将组件列表从组件池中销毁
        idToComponent.forEach((id, component) -> {
            componentMap.remove(id);
            ComponentInfo componentInfo = componentClassToInfo.get((Class<Component>) component.getClass());
            componentInfo.pool.remove(component);
            componentInfo.entitySet.remove(entityID.getId());
        });
    }

    /**
     根据传入的组件对象列表创建实体,可链式调用
     */
    public Comonponter createEntity(Component... components) {
        createEntityAndReturn(components);
        return this;
    }

    /**
     根据传入的组件对象列表创建实体,并返回这个实体对象
     */
    public EntityID createEntityAndReturn(Component... components) {
        EntityID entityID = EntityIDGenerator.getInstance().Gen();//生成实体id
        // 添加组件
        for (Component component : components) {
            ComponentID componentID = ComponentIDGenerator.getInstance().Gen();//生成组件ID
            componentMap.put(componentID, component);
            ComponentInfo componentInfo = componentClassToInfo.computeIfAbsent((Class<Component>) component.getClass(), k -> new ComponentInfo());
            componentInfo.pool.add(component);
            componentInfo.entitySet.add(entityID.getId());// 与该组件相关联的实体
            HashMap<ComponentID, Component> componentContainer = entities
                    .computeIfAbsent(entityID, k -> new HashMap<>());
            componentContainer.put(componentID, component);// 组件ID关联组件
        }
        return entityID;
    }

    public void clear() {
        componentMap.clear();
        componentClassToInfo.clear();//class -> info -> pool
        entities.clear();
    }
}
