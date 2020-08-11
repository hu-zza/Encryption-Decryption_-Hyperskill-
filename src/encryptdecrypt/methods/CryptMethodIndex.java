package encryptdecrypt.methods;

class CryptMethodIndex {
    static void registerAllMethods() {
        CryptMethod.register("SHIFT", new ShiftLatin());
        CryptMethod.register("UNICODE", new ShiftUnicode());
    }
}
