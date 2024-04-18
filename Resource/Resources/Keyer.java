package com.jjdx.ecosystem.Resource.Resources;

import com.jjdx.ecosystem.Resource.Resource;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

/**
 键盘事件监听
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */

public class Keyer extends Resource {
    public Keyer(Consumer<KeyEvent> keyTypedConsumer, Consumer<KeyEvent> keyPressedConsumer, Consumer<KeyEvent> keyReleasedConsumer) {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (keyTypedConsumer != null) keyTypedConsumer.accept(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (keyPressedConsumer != null) keyPressedConsumer.accept(e);
                System.out.println("??");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keyReleasedConsumer != null) keyReleasedConsumer.accept(e);
            }
        };
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof KeyEvent) {
                System.out.println("key");
                keyListener.keyTyped((KeyEvent) event);
                keyListener.keyPressed((KeyEvent) event);
                keyListener.keyReleased((KeyEvent) event);
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    @Override
    public void destroy() {

    }


}
