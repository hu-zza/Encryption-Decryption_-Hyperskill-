package encryptdecrypt.methods;

public class CryptMethodFactory {
    public static CryptMethod create(String type, String... args) {
        switch (type.toUpperCase()) {
            case "SHIFT":
                return new ShiftLatin(args);
            case "UNICODE":
                return new ShiftUnicode(args);
            default:
                return null;
        }
    }
}
