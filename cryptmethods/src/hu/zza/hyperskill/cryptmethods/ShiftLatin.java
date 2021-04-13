package hu.zza.hyperskill.cryptmethods;

import hu.zza.hyperskill.cryptservice.CryptService;

public class ShiftLatin extends CryptMethod implements CryptService {

  private int key = 0;
  private boolean encryption;
  private int charInInt;
  private CharType charType;

  @Override
  public String getName() {
    return "SHIFT";
  }

  @Override
  public void setArguments(String... arguments) {
    super.setArguments(arguments);
    key = Integer.parseInt(argsMap.getOrDefault("-key", "0"));
  }

  @Override
  public String encryptText(String inputText) {
    encryption = true;
    return convertText(inputText);
  }

  @Override
  public String decryptText(String inputText) {
    encryption = false;
    return convertText(inputText);
  }

  private String convertText(String inputText) {
    convertedText.setLength(0);

    for (char ch : inputText.toCharArray()) {
      charInInt = ch;
      charType = analyzeChar(ch);

      if (charType != CharType.NO_CONV) {
        convertChar();
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

  private void convertChar() {
    boolean isUpper = charType == CharType.UPPER;
    char[] thresholds = {'Z', 'z', 'A', 'a'};
    char threshold = thresholds[(encryption ? 0 : 2) + (isUpper ? 0 : 1)];

    charInInt += encryption ? key : -key;

    while (encryption ? threshold < charInInt : threshold > charInInt) {
      charInInt += encryption ? -26 : 26;
    }
  }

  private enum CharType {
    UPPER,
    LOWER,
    NO_CONV
  }
}
