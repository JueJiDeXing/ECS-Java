package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Component.ComponentIDGenerator;
import com.jjdx.ecosystem.Component.ComponentInfo;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Entity.EntityGenerator;
import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.Resource.ResourceIDGenerator;
import lombok.Getter;

import java.util.*;

/**
 命令,提供[实体|资源类]的创建销毁接口
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Commands {
    World world;
    @Getter
    private static final Commands instance = new Commands();

    private Commands() {}

    public static void setWorld(World world) {
        instance.world = world;
    }


    /**
     销毁实体
     */
    private void destroyEntity(EntityID entityID) {
        if (!world.entities.containsKey(entityID)) return;
        //实体关联组件列表
        HashMap<ComponentID, Component> idToComponent = world.entities.get(entityID);
        world.entities.remove(entityID);
        //将组件列表从组件池中销毁
        idToComponent.forEach((id, component) -> {
            world.componentMap.remove(id);
            ComponentInfo componentInfo = world.componentClassToInfo.get((Class<Component>) component.getClass());
            componentInfo.pool.remove(component);
            componentInfo.entitySet.remove(entityID.getId());
        });
    }

    /**
     根据传入的组件对象列表创建实体,可链式调用
     */
    public Commands createEntity(Component... components) {
        createEntityAndReturn(components);
        return this;
    }

    /**
     根据传入的组件对象列表创建实体,并返回这个实体对象
     */
    public EntityID createEntityAndReturn(Component... components) {
        EntityID entityID = EntityGenerator.getInstance().Gen();//生成实体id
        // 添加组件
        for (Component component : components) {
            ComponentID componentID = ComponentIDGenerator.getInstance().Gen();//生成组件ID
            world.componentMap.put(componentID, component);
            ComponentInfo componentInfo = world.componentClassToInfo.computeIfAbsent((Class<Component>) component.getClass(), k -> new ComponentInfo());
            componentInfo.pool.add(component);
            componentInfo.entitySet.add(entityID.getId());// 与该组件相关联的实体
            HashMap<ComponentID, Component> componentContainer = world.entities
                    .computeIfAbsent(entityID, k -> new HashMap<>());
            componentContainer.put(componentID, component);// 组件ID关联组件
        }
        return entityID;
    }





}
