package com.jjdx.ecosystem.System.StartSystems;

import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.Util.ClassUtil;

import java.util.List;

/**
 启动系统管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class StartSystemManger {

    public static List<StartUpSystem> getSysList() {
        return ClassUtil.scan(StartSystemManger.class, StartUpSystem.class);
    }
}
