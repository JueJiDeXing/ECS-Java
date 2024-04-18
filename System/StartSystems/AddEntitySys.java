package com.jjdx.ecosystem.System.StartSystems;

import com.jjdx.ecosystem.Component.Components.Name;
import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.World.Commands;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.Resourcer;

import java.util.*;

/**
 测试: 启动时添加一些实体
 <br>
 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br>
 */
public class AddEntitySys extends StartUpSystem {

    public void run(Commands commands, Queryer queryer, Resourcer resourcer, Eventer eventer) {
        commands.createEntity(new Name("张三"), new Pos(0, 0), new Speed(1, 1))
                .createEntity(new Name("石头"), new Pos(1, 1));
    }

}
