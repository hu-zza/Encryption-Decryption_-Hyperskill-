package hu.zza.hyperskill.cryptmethods;

import hu.zza.hyperskill.cryptservice.CryptService;

public class ShiftUnicode extends CryptMethod implements CryptService {

  private int key = 0;

  @Override
  public String getName() {
    return "UNICODE";
  }

  @Override
  public void setArguments(String... arguments) {
    super.setArguments(arguments);
    key = Integer.parseInt(argsMap.getOrDefault("-key", "0"));
  }

  @Override
  public String encryptText(String inputText) {
    return convertText(inputText, true);
  }

  @Override
  public String decryptText(String inputText) {
    return convertText(inputText, false);
  }

  private String convertText(String inputText, boolean encryption) {
    convertedText.setLength(0);

    for (char ch : inputText.toCharArray()) {
      convertedText.append((char) (encryption ? ch + key : ch - key));
    }
    return convertedText.toString();
  }
}
