package com.jjdx.ecosystem.System.Systems;

import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.System.UpdateSystem;

import java.util.ArrayList;
import java.util.List;

/**
 更新系统
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class UpdateSysList {

    public static List<UpdateSystem> getSysList() {
        List<UpdateSystem> systemList = new ArrayList<>();
        systemList.add((commands, queryer, resourcer, eventer) -> {
            // 根据速度更新位置
            List<EntityID> canMoveEntity = queryer.getEntityWithComponents(Pos.class, Speed.class);
            for (EntityID entityID : canMoveEntity) {
                Pos pos = (Pos) queryer.getComponent(entityID, Pos.class);
                Speed speed = (Speed) queryer.getComponent(entityID, Speed.class);
                pos.setX(pos.getX() + speed.getSpeedX());
                pos.setY(pos.getY() + speed.getSpeedY());
            }
        });
        return systemList;
    }
}
