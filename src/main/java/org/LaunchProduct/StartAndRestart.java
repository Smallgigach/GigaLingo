package org.LaunchProduct;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import org.ApiAndKeyBoard.GlobalKeyListener;

import java.util.Scanner;

public class StartAndRestart {
   public static String source;
    public static String target;
    public void startProgram() throws NativeHookException {
        System.out.println("Добро пожаловать в GigaLingo,здесь вы сможете автоматически переводить нужны вам слова/предложения/текста абсолютно бесплатно!");
        System.out.println("Если хотите остановить программу используйте сочитание клавищ: ctrl+r");
        System.out.println("Введите язык с которого вы хотите перевести:");
        Scanner scanner = new Scanner(System.in);
        source = scanner.nextLine();
        System.out.println("Введите язык,на какой вы хотите перевести:");
        target = scanner.nextLine();
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }

}
