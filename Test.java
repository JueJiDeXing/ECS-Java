package com.jjdx.ecosystem;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Component.Components.Name;
import com.jjdx.ecosystem.Component.Components.Pos;
import com.jjdx.ecosystem.Component.Components.Speed;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.EventReader;
import com.jjdx.ecosystem.Event.EventWriter;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.Resource.Resources.Inputer;
import com.jjdx.ecosystem.System.StartUpSystem;
import com.jjdx.ecosystem.System.UpdateSystem;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.World;

import java.util.ArrayList;
import java.util.List;

/**
 测试类
 <br>

 @ Author: 绝迹的星  <br>
 @ Time: 2024/4/16  <br> */
public class Test {
    static List<UpdateSystem> UpdateSystems() {
        List<UpdateSystem> systemList = new ArrayList<>();
        systemList.add((commands, queryer, resourcer, eventer) -> {
            // 根据速度更新位置
            List<EntityID> canMoveEntity = queryer.getEntityWithComponents(Pos.class, Speed.class);
            for (EntityID entityID : canMoveEntity) {
                Pos pos = (Pos) queryer.getComponent(entityID, Pos.class);
                Speed speed = (Speed) queryer.getComponent(entityID, Speed.class);
                pos.setX(pos.getX() + speed.getSpeedX());
                pos.setY(pos.getY() + speed.getSpeedY());
            }
        });
        return systemList;
    }

    static List<StartUpSystem> StartUpSystems() {
        List<StartUpSystem> systemList = new ArrayList<>();
        systemList.add((commands, queryer, resourcer, eventer) -> { //添加一些实体
            commands.createEntity(new Name("张三"), new Pos(1, 1), new Speed(1, 1))
                    .createEntity(new Name("李四"), new Pos(2, 2), new Speed(2, 2));
        });
        return systemList;
    }

    public static void main(String[] args) {
        World world = new World();
        EventReader<String> reader = Eventer.reader("");
        EventWriter<String> writer = Eventer.writer("");
        world.addStartUpSystem(StartUpSystems())//启动系统
                .addUpdateSystem(UpdateSystems())// 更新系统
                .setResource(new Inputer(writer));//插入一个控制台输入监听器
        world.startUp();//调用启动系统
        Queryer queryer = Queryer.getInstance();//查询器,可查询实体
        while (true) {
            if (reader.isHave()) {//读取控制台输入
                String read = reader.read();
                System.out.println("read:" + read);
                if (read.equals("exit")) {//输入exit退出程序
                    world.shutdown();
                    break;
                }
            }
            System.out.println("--------query-------");
            List<EntityID> allEntity = queryer.getAllEntity();//查询全部实体
            for (EntityID entityID : allEntity) {
                List<Component> entityComponent = queryer.getEntityComponent(entityID);//获取到实体绑定的组件
                System.out.println(entityComponent);
            }
            world.update();//调用更新系统
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
