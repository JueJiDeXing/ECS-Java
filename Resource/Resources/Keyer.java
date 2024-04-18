package com.jjdx.ecosystem.Resource.Resources;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.jjdx.ecosystem.Resource.Resource;

import java.util.function.Consumer;

/**
 键盘事件监听<br>
 使用第三方库jnativehook
 <br>

 @ Author: 绝迹的星 <br>
 @ Time: 2024/4/18 <br> */
public class Keyer extends Resource implements NativeKeyListener {

    @Override
    public void destroy() {
        removeListener();//移除监听,使线程中断
    }

    Consumer<String> keyPressed;
    Consumer<String> keyReleased;
    Consumer<String> keyTyped;

    /**
     例如:keyPressed = keyText -> {
     if (keyText.equals("0")) {
     System.out.println("0被按下");
     }
     }

     @param keyPressed  事件处理函数
     @param keyReleased 事件处理函数
     @param keyTyped    事件处理函数
     */
    public Keyer(Consumer<String> keyPressed, Consumer<String> keyReleased, Consumer<String> keyTyped) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {

            System.err.println("键盘监听初始化发生错误: ");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        this.keyPressed = keyPressed;
        this.keyReleased = keyReleased;
        this.keyTyped = keyTyped;
        setListener();
    }


    public void setListener() {
        GlobalScreen.addNativeKeyListener(this);
    }

    public void removeListener() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (keyPressed != null) keyPressed.accept(getKeyText(e));
    }

    private static String getKeyText(NativeKeyEvent e) {
        return NativeKeyEvent.getKeyText(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (keyReleased != null) keyReleased.accept(getKeyText(e));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        if (keyTyped != null) keyTyped.accept(getKeyText(e));
    }
}
