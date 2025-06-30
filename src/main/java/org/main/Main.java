package org.main;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import org.ApiAndKeyBoard.GlobalKeyListener;


public class Main {
    public static void main(String[] args) throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }
}