package com.jjdx.ecosystem.World;

import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.System.OtherSystem;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.System.UpdateSystem;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 系统管理器
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class Systemer {
    World world;
    /**
     系统
     */
    LinkedList<StartUpSystem> startUpSystems = new LinkedList<>();
    LinkedList<UpdateSystem> updateSystems = new LinkedList<>();
    LinkedList<OtherSystem> otherSystems = new LinkedList<>();
    @Getter
    private static final Systemer instance = new Systemer();

    private Systemer() {
    }

    public static void setWorld(World world) {
        instance.world = world;
    }

    public Systemer addStartUpSystem(StartUpSystem sys) {
        startUpSystems.add(sys);
        return this;
    }

    public Systemer addUpdateSystem(UpdateSystem sys) {
        updateSystems.add(sys);
        return this;
    }

    public Systemer addOtherSystem(OtherSystem sys) {
        otherSystems.add(sys);
        return this;
    }

    public Systemer addUpdateSystem(List<UpdateSystem> sys) {
        updateSystems.addAll(sys);
        return this;
    }

    public Systemer addStartUpSystem(List<StartUpSystem> sys) {
        startUpSystems.addAll(sys);
        return this;
    }

    public void clear() {
        startUpSystems.clear();
        updateSystems.clear();
        otherSystems.clear();
    }

    public void startUp() {
        for (StartUpSystem sys : startUpSystems) {
            sys.run(Comonponter.getInstance(),
                    Queryer.getInstance(),
                    Resourcer.getInstance(),
                    Eventer.getInstance());
        }
    }

    public void update() {
        for (UpdateSystem sys : updateSystems) {
            sys.run(Comonponter.getInstance(),
                    Queryer.getInstance(),
                    Resourcer.getInstance(),
                    Eventer.getInstance());
        }
    }

    public void doOther() {
        for (OtherSystem sys : otherSystems) {
            sys.run();
        }
    }
}
