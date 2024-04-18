package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.ComponentID;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.System.OtherSystem;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.System.UpdateSystem;
import com.jjdx.ecosystem.Timer.TimerTasker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 世界类,管理Component和System
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class World {
    Comonponter comonponter = Comonponter.getInstance();
    Queryer queryer = Queryer.getInstance();
    Eventer eventer = Eventer.getInstance();
    Resourcer resourcer = Resourcer.getInstance();
    Systemer systemer = Systemer.getInstance();

    public World setResource(Resource resource) {
        resourcer.setResource(resource);
        return this;
    }

    public World removeResource(Class<? extends Resource> resourceClass) {
        resourcer.removeResource(resourceClass);
        return this;
    }

    public World addStartUpSystem(StartUpSystem sys) {
        systemer.addStartUpSystem(sys);
        return this;
    }

    public World addUpdateSystem(UpdateSystem sys) {
        systemer.addUpdateSystem(sys);
        return this;
    }

    public World addOtherSystem(OtherSystem sys) {
        systemer.addOtherSystem(sys);
        return this;
    }

    public World addUpdateSystem(List<UpdateSystem> sys) {
        systemer.addUpdateSystem(sys);
        return this;
    }

    public World addStartUpSystem(List<StartUpSystem> sys) {
        systemer.addStartUpSystem(sys);
        return this;
    }

    public void startUp() {
        systemer.startUp();

    }

    public void update() {
        systemer.update();
        eventer.applyEvents();
    }

    public void otherSysUpdate() {
        systemer.doOther();

    }

    public void shutdown() {
        comonponter.clear();
        resourcer.clear();
        Systemer.getInstance().clear();
        TimerTasker.getInstance().removeAllTimer();
    }

    public String toStringEntity() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entity(").append(comonponter.entities.size()).append("): ").append("[");
        for (Map.Entry<EntityID, HashMap<ComponentID, Component>> entry : comonponter.entities.entrySet()) {
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
        sb.append("Component(").append(comonponter.componentMap.size()).append("): ").append("[");
        for (Map.Entry<ComponentID, Component> entry : comonponter.componentMap.entrySet()) {
            Component component = entry.getValue();
            sb.append(component).append(", ");
        }
        sb.append("\b\b]");
        return sb.toString();
    }

    public String toStringResource() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resource(").append(resourcer.idToResource.size()).append("): ").append("[");
        for (Map.Entry<ResourceID, Resource> entry : resourcer.idToResource.entrySet()) {
            Resource component = entry.getValue();
            sb.append(component).append(", ");
        }
        sb.append("\b\b]");
        return sb.toString();
    }

    public String toStringSystem() {
        return "System(" + (systemer.startUpSystems.size() + systemer.updateSystems.size()) + "): " +
                systemer.startUpSystems + ", " + systemer.updateSystems;
    }

    @Override
    public String toString() {
        return "World{" +
                "Entity(" + comonponter.entities.size() +
                "), Component(" + comonponter.componentMap.size() +
                "), Resource(" + resourcer.idToResource.size() +
                "), System(" + systemer.startUpSystems.size() + systemer.updateSystems.size() +
                ")}";
    }
}
