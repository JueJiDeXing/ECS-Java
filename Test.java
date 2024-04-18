package com.jjdx.ecosystem;

import com.jjdx.ecosystem.Component.Component;
import com.jjdx.ecosystem.Entity.EntityID;
import com.jjdx.ecosystem.Event.EventReader;
import com.jjdx.ecosystem.Event.EventWriter;
import com.jjdx.ecosystem.Event.Eventer;
import com.jjdx.ecosystem.Resource.Resources.Inputer;
import com.jjdx.ecosystem.Resource.Resources.Keyer;
import com.jjdx.ecosystem.System.StartSystems.StartSystemManger;
import com.jjdx.ecosystem.System.UpdateSystems.UpdateSystemManger;
import com.jjdx.ecosystem.World.Queryer;
import com.jjdx.ecosystem.World.World;

import java.util.List;

/**
 测试类
 <br>

 @ Author: 绝迹的星  <br>
 @ Time: 2024/4/16  <br> */
public class Test {
    public static void main(String[] args) {
        World world = new World();
        EventReader<String> reader = Eventer.reader("");
        EventWriter<String> writer = Eventer.writer("");
        world.addStartUpSystem(StartSystemManger.getSysList())// 启动系统
                .addUpdateSystem(UpdateSystemManger.getSysList())// 更新系统
                .setResource(new Inputer(writer)) // 插入一个控制台输入监听器
                .setResource(new Keyer(keyText -> {
                    if (keyText.equals("0")) System.out.println("--1--");
                }, null, null));//全局键盘事件监听器
        world.startUp();//调用启动系统
        Queryer queryer = Queryer.getInstance();//查询器,可查询实体
        queryEntityAndPrint(queryer);
        while (true) {
            if (reader.isHave()) {
                String text = reader.read();
                break;
            }
            world.update();
            sleep(1000);
        }
        queryEntityAndPrint(queryer);
        world.shutdown();
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void queryEntityAndPrint(Queryer queryer) {
        System.out.println("--------query-------");
        List<EntityID> allEntity = queryer.getAllEntity();//查询全部实体
        for (EntityID entityID : allEntity) {
            List<Component> entityComponent = queryer.getEntityComponent(entityID);//获取到实体绑定的组件
            System.out.println(entityComponent);
        }
    }
}
