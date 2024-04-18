package com.jjdx.ecosystem.System;

import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.World.Comonponter;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.Resourcer;

/**
 系统(行为)
 <br>

 @ Author: 绝迹的星<br>
 @ Time: 2024/4/17<br> */
public interface System {
    default void run(Comonponter comonponter, Queryer queryer, Resourcer resourcer, Eventer eventer) {
    }
}
