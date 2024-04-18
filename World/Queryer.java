package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Entity.EntityID;
import lombok.Getter;

import java.util.*;

/**
 组件查询器,依赖于组件管理器,用于查询实体和组件
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Queryer {

    @Getter
    private static final Queryer instance = new Queryer();

    Comonponter comonponter = Comonponter.getInstance();

    private Queryer() {
    }


    /**
     获取所有实体
     */
    public List<EntityID> getAllEntity() {
        return comonponter.entities.keySet().stream().toList();
    }


    /**
     获取实体的某一指定组件类型的组件对象

     @param componentClass 组件类型
     */
    public Component getComponent(EntityID entityID, Class<? extends Component> componentClass) {
        HashMap<ComponentID, Component> idToComponent = comonponter.entities.get(entityID);
        for (Component component : idToComponent.values()) {
            if (componentClass.equals(component.getClass())) return component;
        }
        return null;
    }

    /**
     获取实体的某些指定组件类型的组件对象

     @param componentClass 组件类型,只要实体的组件x在componentClass中即加入答案
     */
    @SafeVarargs
    public final List<Component> getComponent(EntityID entityID, Class<? extends Component>... componentClass) {
        List<Component> ans = new ArrayList<>();
        HashSet<Class<? extends Component>> set = new HashSet<>(Arrays.asList(componentClass));
        HashMap<ComponentID, Component> idToComponent = comonponter.entities.get(entityID);
        for (Component component : idToComponent.values()) {
            if (set.contains(component.getClass())) ans.add(component);
        }
        return ans;
    }

    /**
     获取关联了全部指定类型组件的实体

     @param componentClass 组件类型,当实体的组件列表全部包含componentClass即加入答案
     */
    @SafeVarargs
    public final List<EntityID> getEntityWithComponents(Class<? extends Component>... componentClass) {
        List<EntityID> entities = new ArrayList<>();
        List<Class<? extends Component>> list = Arrays.asList(componentClass);
        Set<Class<? extends Component>> set = new HashSet<>();
        for (Map.Entry<EntityID, HashMap<ComponentID, Component>> entry : comonponter.entities.entrySet()) {//遍历所有实体
            //该组件拥有的 所有组件类
            set.clear();
            Collection<Component> values = entry.getValue().values();
            values.forEach(component -> set.add(component.getClass()));
            //需要的组件类 全部存在
            if (set.containsAll(list)) entities.add(entry.getKey());
        }
        return entities;
    }

    /**
     判断实体是否存在
     */
    public boolean isContain(EntityID entityID) {
        return comonponter.entities.containsKey(entityID);
    }

    /**
     获取实体关联的组件
     */
    public HashMap<ComponentID, Component> getComponentOfEntity(EntityID entityID) {
        return comonponter.entities.get(entityID);
    }

    /**
     获取实体关联的全部组件
     */
    public List<Component> getEntityComponent(EntityID entityID) {
        return new ArrayList<>(comonponter.entities.get(entityID).values());
    }
}
