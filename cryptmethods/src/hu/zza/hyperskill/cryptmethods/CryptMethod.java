package hu.zza.hyperskill.cryptmethods;

import java.util.HashMap;

abstract class CryptMethod {
  protected final StringBuilder convertedText = new StringBuilder();
  protected final HashMap<String, String> argsMap = new HashMap<>();

  public String getDescription() {
    return "This is a cryptographic method which provides a service for package hu.zza.hyperskill.encryptdecrypt.";
  }

  public void setArguments(String... arguments) {
    argsMap.clear();
    for (int x = 0; x < arguments.length / 2; x++) {
      this.argsMap.put(arguments[2 * x], arguments[2 * x + 1]);
    }
  }
}
