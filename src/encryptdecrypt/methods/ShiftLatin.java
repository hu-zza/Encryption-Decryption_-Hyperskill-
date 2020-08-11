package encryptdecrypt.methods;

public class ShiftLatin extends CryptMethod {
    int charInInt;

    public ShiftLatin(String... args) {
        super(args);
    }

    @Override
    public String encryptText(String inputText) {

        convertedText.setLength(0);
        int key = Integer.parseInt(args.getOrDefault("key", "0"));

        for (char ch : inputText.toCharArray()) {
            charInInt = ch;
            switch (analyzeChar(ch)) {
                case UPPER:
                    charInInt += key;
                    while ('Z' < charInInt) {
                        charInInt -= 26;
                    }
                    break;

                case LOWER:
                    charInInt += key;
                    while ('z' < charInInt) {
                        charInInt -= 26;
                    }
                    break;
            }
            convertedText.append((char) charInInt);
        }

        return convertedText.toString();
    }

    @Override
    public String decryptText(String inputText) {

        convertedText.setLength(0);
        int key = Integer.parseInt(args.getOrDefault("key", "0"));

        for (char ch : inputText.toCharArray()) {
            charInInt = ch;
            switch (analyzeChar(ch)) {
                case UPPER:
                    charInInt -= key;
                    while ('A' > charInInt) {
                        charInInt += 26;
                    }
                    break;

                case LOWER:
                    charInInt -= key;
                    while ('a' > charInInt) {
                        charInInt += 26;
                    }
                    break;
            }
            convertedText.append((char) charInInt);
        }

        return convertedText.toString();
    }

    private CharType analyzeChar(char ch) {
        if ('A' <= ch && ch <= 'Z') {
            return CharType.UPPER;
        }

        if ('a' <= ch && ch <= 'z') {
            return CharType.LOWER;
        }

        return CharType.NO_CONV;
    }

    private enum CharType {
        UPPER,
        LOWER,
        NO_CONV
    }
}