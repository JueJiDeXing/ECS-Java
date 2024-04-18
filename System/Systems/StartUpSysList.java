package com.jjdx.ecosystem.System.Systems;

import com.jjdx.ecosystem.Component.Components.Name;
import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.System.StartUpSystem;
import java.util.*;

/**
 启动系统
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class StartUpSysList {

    public static List<StartUpSystem> getSysList() {
        List<StartUpSystem> systemList = new ArrayList<>();
        systemList.add((commands, queryer, resourcer, eventer) -> { //添加一些实体
            commands.createEntity(new Name("张三"), new Pos(1, 1), new Speed(1, 1))
                    .createEntity(new Name("李四"), new Pos(2, 2), new Speed(2, 2));
        });
        return systemList;
    }
}
