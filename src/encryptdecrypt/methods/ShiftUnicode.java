package encryptdecrypt.methods;

public class ShiftUnicode extends  CryptMethod {

    public ShiftUnicode(String... args) {
        super(args);
    }

    @Override
    public String encryptText(String inputText) {

        convertedText.setLength(0);
        int key = Integer.parseInt(args.getOrDefault("key", "0"));

        for (char ch : inputText.toCharArray()) {
            convertedText.append((char) (ch + key));
        }

        return convertedText.toString();
    }

    @Override
    public String decryptText(String inputText) {

        convertedText.setLength(0);
        int key = Integer.parseInt(args.getOrDefault("key", "0"));

        for (char ch : inputText.toCharArray()) {
            convertedText.append((char) (ch - key));
        }

        return convertedText.toString();
    }
}