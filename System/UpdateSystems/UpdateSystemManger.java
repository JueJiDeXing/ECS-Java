package com.jjdx.ecosystem.System.UpdateSystems;

import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.System.UpdateSystem;
import com.jjdx.ecosystem.Util.ClassUtil;
import com.jjdx.ecosystem.World.Commands;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.Resourcer;

import java.util.ArrayList;
import java.util.List;

/**
 更新系统管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class UpdateSystemManger {

    public static List<UpdateSystem> getSysList() {
        return ClassUtil.scan(UpdateSystem.class);
    }
}
