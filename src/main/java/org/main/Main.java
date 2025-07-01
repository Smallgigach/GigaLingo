package org.main;

import com.github.kwhat.jnativehook.NativeHookException;
import org.LaunchProduct.StartAndRestart;


public class Main {
    public static void main(String[] args) throws NativeHookException {
     StartAndRestart startAndRestart = new StartAndRestart();
     startAndRestart.startProgram();
    }
}