package com.jjdx.ecosystem.System.StartSystems;

import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.World.Comonponter;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.Resourcer;

/**
 测试: 启动时添加一些实体
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class AddEntitySys extends StartUpSystem {


    public void run(Comonponter comonponter, Queryer queryer, Resourcer resourcer, Eventer eventer) {
        comonponter.createEntity(new Pos(0, 0),
                new Speed(1, 1));

    }

}
