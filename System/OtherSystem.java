package com.jjdx.ecosystem.System;

/**
 其他系统,指需要依赖其他模块的系统,如绘制系统,需要画笔和画布对象进行绘制
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public abstract class OtherSystem implements System {

    public abstract void run();
}
