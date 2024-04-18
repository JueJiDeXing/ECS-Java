package com.jjdx.ecosystem.System.UpdateSystems;

import com.jjdx.ecosystem.System.UpdateSystem;
import com.jjdx.ecosystem.Util.ClassUtil;

import java.util.List;

/**
 更新系统管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class UpdateSystemManger {

    public static List<UpdateSystem> getSysList() {
        return ClassUtil.scan(UpdateSystemManger.class, UpdateSystem.class);
    }
}
