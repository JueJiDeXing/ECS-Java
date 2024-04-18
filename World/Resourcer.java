package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Resource.Resource;
import com.jjdx.ecosystem.Resource.ResourceID;
import com.jjdx.ecosystem.Resource.ResourceIDGenerator;
import lombok.Getter;

/**
 资源管理器
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public class Resourcer {
    World world;
    @Getter
    private static final Resourcer instance = new Resourcer();

    private Resourcer() {
    }

    public static void setWorld(World world) {
        instance.world = world;
    }

    /**
     判断world上是否存在指定资源类的对象
     */
    boolean isContain(Class<? extends Resource> resourceClass) {
        return world.resourceClassToID.containsKey(resourceClass);
    }

    /**
     获取挂载到world上的指定资源类的对象
     */
    public Resource getResource(Class<? extends Resource> resourceClass) {
        if (!isContain(resourceClass)) {
            throw new RuntimeException("Resource " + resourceClass.getName() + " not exist");
        }
        ResourceID resourceID = world.resourceClassToID.get(resourceClass);
        return world.idToResource.get(resourceID);
    }

    /**
     给world添加资源
     */
    public Resourcer setResource(Resource resource) {
        if (world.idToResource.containsValue(resource)) return this;
        ResourceID resourceID = ResourceIDGenerator.getInstance().Gen();
        world.resourceClassToID.put((Class<Resource>) resource.getClass(), resourceID);
        world.idToResource.put(resourceID, resource);
        return this;
    }

    /**
     移除资源
     */
    public Resourcer removeResource(Class<? extends Resource> resourceClass) {
        if (!world.resourceClassToID.containsKey(resourceClass)) return this;
        ResourceID resourceID = world.resourceClassToID.get(resourceClass);
        world.resourceClassToID.remove(resourceClass);
        Resource resource = world.idToResource.remove(resourceID);
        resource.destroy();
        return this;
    }
}
