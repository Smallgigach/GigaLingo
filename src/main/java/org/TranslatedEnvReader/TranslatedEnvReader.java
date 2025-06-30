package org.TranslatedEnvReader;

import io.github.cdimascio.dotenv.Dotenv;

 public class TranslatedEnvReader {
    protected  String secretKeyIdEnv;
    protected  String secretKeyEnv;
   public TranslatedEnvReader() {
        getEnv();
    }
    private final void  getEnv() {
        Dotenv dotenv = Dotenv.load();
       secretKeyIdEnv = dotenv.get("LARA_ACCESS_KEY_ID");
        secretKeyEnv =  dotenv.get("LARA_ACCESS_KEY_SECRET");
    }
}
