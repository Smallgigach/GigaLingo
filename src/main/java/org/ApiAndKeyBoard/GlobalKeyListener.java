package org.ApiAndKeyBoard;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.translated.lara.Credentials;
import com.translated.lara.errors.LaraException;
import com.translated.lara.translator.TextResult;
import com.translated.lara.translator.Translator;
import org.LaunchProduct.StartAndRestart;
import org.TranslatedEnvReader.TranslatedEnvReader;
import org.main.Main;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
public class GlobalKeyListener extends TranslatedEnvReader  implements NativeKeyListener {
  protected    String data;
     public void nativeKeyPressed(NativeKeyEvent e) {
         try {
             if (e.getKeyCode() == NativeKeyEvent.VC_C && (e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0) {
                 Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                 try {
                     data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                     if (data == null || data.isEmpty()) {
                         System.out.println("Данные не были скопированы или произошла ошибка!");
                     }
                 } catch (UnsupportedFlavorException | IOException exception) {
                     throw new RuntimeException(exception);
                 }

                 try {
                     StartAndRestart startAndRestart = new StartAndRestart();
                     CompletableFuture<Void> future =  CompletableFuture.runAsync(() -> {
                         Credentials cred = new Credentials(secretKeyIdEnv, secretKeyEnv);
                         Translator translator = new Translator(cred);
                         try {
                             TextResult result = translator.translate(data, startAndRestart.source, startAndRestart.target);
                             System.out.println("Перевод: " + result.getTranslation());

                         } catch (LaraException ex) {
                             System.err.println("нвозможно перевести строку!");
                             throw new RuntimeException(ex);
                         }

                     });
                     System.out.println("Основной поток робит...");

                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }
             if(e.getKeyCode() == NativeKeyEvent.VC_R && (e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0) {
                 System.exit(0);
             }
         }
          catch (Exception ex) {
             ex.printStackTrace();
          }

     }


 }
