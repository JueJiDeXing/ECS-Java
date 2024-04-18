package com.jjdx.ecosystem.Resource;

import com.jjdx.ecosystem.World.World;

import java.util.*;

/**
 Resource是资源类,如系统计时器Timer,输入Inputer等
 通常为系统单例
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public abstract class Resource {
    public abstract void destroy();
}
