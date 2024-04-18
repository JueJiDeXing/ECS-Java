package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.Resource.ResourceIDGenerator;
import lombok.Getter;

import java.util.HashMap;

/**
 资源管理器
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Resourcer {
    World world;
    @Getter
    private static final Resourcer instance = new Resourcer();
    /**
     resource类 -> resource对象id
     */
    HashMap<Class<Resource>, ResourceID> resourceClassToID = new HashMap<>();
    /**
     resource对象id -> resource对象
     */
    HashMap<ResourceID, Resource> idToResource = new HashMap<>();

    private Resourcer() {
    }

    public static void setWorld(World world) {
        instance.world = world;
    }

    /**
     判断world上是否存在指定资源类的对象
     */
    boolean isContain(Class<? extends Resource> resourceClass) {
        return resourceClassToID.containsKey(resourceClass);
    }

    /**
     获取挂载到world上的指定资源类的对象
     */
    public Resource getResource(Class<? extends Resource> resourceClass) {
        if (!isContain(resourceClass)) {
            throw new RuntimeException("Resource " + resourceClass.getName() + " not exist");
        }
        ResourceID resourceID = resourceClassToID.get(resourceClass);
        return idToResource.get(resourceID);
    }

    /**
     给world添加资源
     */
    public Resourcer setResource(Resource resource) {
        if (idToResource.containsValue(resource)) return this;
        ResourceID resourceID = ResourceIDGenerator.getInstance().Gen();
        resourceClassToID.put((Class<Resource>) resource.getClass(), resourceID);
        idToResource.put(resourceID, resource);
        return this;
    }

    /**
     移除资源
     */
    public Resourcer removeResource(Class<? extends Resource> resourceClass) {
        if (!resourceClassToID.containsKey(resourceClass)) return this;
        ResourceID resourceID = resourceClassToID.get(resourceClass);
        resourceClassToID.remove(resourceClass);
        Resource resource = idToResource.remove(resourceID);
        resource.destroy();
        return this;
    }

    public void clear() {
        resourceClassToID.clear();
        for (Resource resource : idToResource.values()) {
            resource.destroy();
        }
        idToResource.clear();
    }
}
