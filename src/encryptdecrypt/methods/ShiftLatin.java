package encryptdecrypt.methods;

public class ShiftLatin extends CryptMethod {
    int charInInt;

    public ShiftLatin(String mode, int key) {
        super(mode, key);
    }

    @Override
    void encryptText(String inputText) {
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
    }

    @Override
    void decryptText(String inputText) {
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