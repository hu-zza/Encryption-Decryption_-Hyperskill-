package encryptdecrypt.methods;

import java.util.HashMap;

public abstract class CryptMethod {
    final StringBuilder convertedText = new StringBuilder();
    final HashMap<String, String> args;

    public CryptMethod(String... args) {
        this.args = new HashMap<>();
        for (int x = 0; x < args.length / 2; x++) {
            this.args.put(args[2 * x], args[2 * x + 1]);
        }
    }

    public abstract String encryptText(String inputText);
    public abstract String decryptText(String inputText);
}