package encryptdecrypt.methods;

public abstract class CryptMethod {
    final StringBuilder convertedText = new StringBuilder();
    final String mode;
    final int key;

    public CryptMethod(String mode, int key) {
        this.mode = mode;
        this.key = key;
    }

    public String convertText(String inputText) {
        convertedText.delete(0, convertedText.length());

        if ("ENC".equalsIgnoreCase(mode)) {
            encryptText(inputText);
        } else {
            decryptText(inputText);
        }

        return convertedText.toString();
    }

    abstract void encryptText(String inputText);
    abstract void decryptText(String inputText);
}