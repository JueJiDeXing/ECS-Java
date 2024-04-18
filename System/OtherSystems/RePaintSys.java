package com.jjdx.ecosystem.System.OtherSystems;

import com.jjdx.ecosystem.System.OtherSystem;

import java.awt.*;

/**
 示例: 绘制系统,将对象绘制在画布上
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class RePaintSys extends OtherSystem {
    // 需要一个2D画笔对象进行绘制
    Graphics2D g;

    public RePaintSys(Graphics2D g) {
        this.g = g;
    }

    public void run() {

    }
}
