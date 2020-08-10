package encryptdecrypt.methods;

public class ShiftUnicode extends  CryptMethod {

    public ShiftUnicode(String mode, int key) {
        super(mode, key);
    }

    @Override
    void encryptText(String inputText) {
        for (char ch : inputText.toCharArray()) {
            convertedText.append((char) (ch + key));
        }
    }

    @Override
    void decryptText(String inputText) {
        for (char ch : inputText.toCharArray()) {
            convertedText.append((char) (ch - key));
        }
    }
}