package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Component.ComponentInfo;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.Timer.TimerTasker;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.System.UpdateSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 世界类,管理Component和System
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class World {
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
     resource类 -> resource对象id
     */
    HashMap<Class<Resource>, ResourceID> resourceClassToID = new HashMap<>();
    /**
     resource对象id -> resource对象
     */
    HashMap<ResourceID, Resource> idToResource = new HashMap<>();
    /**
     系统
     */
    LinkedList<StartUpSystem> startUpSystems = new LinkedList<>();
    LinkedList<UpdateSystem> updateSystems = new LinkedList<>();

    public World() {
        Commands.setWorld(this);
        Queryer.setWorld(this);
        Resourcer.setWorld(this);
    }

    public World setResource(Resource resource) {
        Resourcer.getInstance().setResource(resource);
        return this;
    }

    public World removeResource(Class<? extends Resource> resourceClass) {
        Resourcer.getInstance().removeResource(resourceClass);
        return this;
    }

    public World addStartUpSystem(StartUpSystem sys) {
        startUpSystems.add(sys);
        return this;
    }

    public World addUpdateSystem(UpdateSystem sys) {
        updateSystems.add(sys);
        return this;
    }

    public World addUpdateSystem(List<UpdateSystem> sys) {
        updateSystems.addAll(sys);
        return this;
    }

    public World addStartUpSystem(List<StartUpSystem> sys) {
        startUpSystems.addAll(sys);
        return this;
    }

    public void startUp() {
        for (StartUpSystem sys : startUpSystems) {
            sys.run(Commands.getInstance(),
                    Queryer.getInstance(),
                    Resourcer.getInstance(),
                    Eventer.getInstance());
        }
    }

    public void update() {
        Eventer eventer = Eventer.getInstance();
        for (UpdateSystem sys : updateSystems) {
            sys.run(Commands.getInstance(),
                    Queryer.getInstance(),
                    Resourcer.getInstance(),
                    eventer);
        }
        eventer.addAllEvents();
    }

    public void shutdown() {
        componentMap.clear();
        componentClassToInfo.clear();//class -> info -> pool
        entities.clear();
        resourceClassToID.clear();
        for (Resource resource : idToResource.values()) {
            resource.destroy();
        }
        idToResource.clear();
        startUpSystems.clear();
        updateSystems.clear();
        TimerTasker.getInstance().removeAllTimer();
    }

    public String toStringEntity() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entity(").append(entities.size()).append("): ").append("[");
        for (Map.Entry<EntityID, HashMap<ComponentID, Component>> entry : entities.entrySet()) {
            HashMap<ComponentID, Component> components = entry.getValue();
            sb.append("{");
            for (Map.Entry<ComponentID, Component> componentEntry : components.entrySet()) {
                sb.append(componentEntry.getValue()).append(", ");
            }
            sb.append("\b\b}, ");
        }
        sb.append("\b\b]");
        return sb.toString();
    }

    public String toStringComponent() {
        StringBuilder sb = new StringBuilder();
        sb.append("Component(").append(componentMap.size()).append("): ").append("[");
        for (Map.Entry<ComponentID, Component> entry : componentMap.entrySet()) {
            Component component = entry.getValue();
            sb.append(component).append(", ");
        }
        sb.append("\b\b]");
        return sb.toString();
    }

    public String toStringResource() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resource(").append(idToResource.size()).append("): ").append("[");
        for (Map.Entry<ResourceID, Resource> entry : idToResource.entrySet()) {
            Resource component = entry.getValue();
            sb.append(component).append(", ");
        }
        sb.append("\b\b]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "World{" +
                "Entity(" + entities.size() +
                "), Component(" + componentMap.size() +
                "), Resource(" + idToResource.size() +
                ")}";
    }
}
