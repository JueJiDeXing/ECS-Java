package com.jjdx.ecosystem.System.UpdateSystems;

import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.System.UpdateSystem;
import com.jjdx.ecosystem.World.Comonponter;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.Resourcer;

import java.util.List;

/**
 测试: 移动系统
 <br>
 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br>
 */
public class MoveSys extends UpdateSystem {

    @Override
    public void run(Comonponter comonponter, Queryer queryer, Resourcer resourcer, Eventer eventer) {
        // 根据速度更新位置
        List<EntityID> canMoveEntity = queryer.getEntityWithComponents(Pos.class, Speed.class);
        for (EntityID entityID : canMoveEntity) {
            Pos pos = (Pos) queryer.getComponent(entityID, Pos.class);
            Speed speed = (Speed) queryer.getComponent(entityID, Speed.class);
            pos.setX(pos.getX() + speed.getSpeedX());
            pos.setY(pos.getY() + speed.getSpeedY());
        }
    }
}
