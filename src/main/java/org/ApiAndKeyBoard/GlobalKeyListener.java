package org.ApiAndKeyBoard;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.translated.lara.Credentials;
import com.translated.lara.translator.TextResult;
import com.translated.lara.translator.Translator;
import org.TranslatedEnvReader.TranslatedEnvReader;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class GlobalKeyListener extends TranslatedEnvReader  implements NativeKeyListener {
  protected    String data;
protected String source = "";
protected String target = "";
// нужно доработать эту ф-цию,чтобы получать данные и отдавать Api
//  public void startProgram() throws NativeHookException {
//      System.out.println("Добро пожаловать в GigaLingo,здесь вы сможете автоматически переводить нужны вам слова/предложения/текста абсолютно бесплатно!");
//      System.out.println("Введите язык с которого вы хотите перевести:");
//      Scanner scanner = new Scanner(System.in);
//       source = scanner.nextLine();
//      System.out.println("Введите язык,на какой вы хотите перевести:");
//      Scanner scanner2 = new Scanner(System.in);
//       target = scanner2.nextLine();
//      GlobalScreen.registerNativeHook();
//      GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
//  }
     public void nativeKeyPressed(NativeKeyEvent e) {
             if (e.getKeyCode() == NativeKeyEvent.VC_C && (e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0) {
                 Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                 try {
                     data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                     if(data == null) {
                         System.out.println("Данные не были скопированы или произошла ошибка!");
                     }
                 } catch (UnsupportedFlavorException |  IOException exception) {
                     throw new RuntimeException(exception);
                 }
                     try {
                         Credentials cred = new Credentials(secretKeyIdEnv, secretKeyEnv);
                         Translator translator = new Translator(cred);
                         TextResult result = translator.translate(data, source, target);
                         System.out.println("Перевод: " + result.getTranslation());
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }

             }

     }

 }
