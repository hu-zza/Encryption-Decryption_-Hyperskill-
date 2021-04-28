package hu.zza.hyperskill.cryptmethods;

import hu.zza.hyperskill.cryptservice.CryptService;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordReverse extends CryptMethod implements CryptService {

  @Override
  public String getName() {
    return "WORD_REVERSE";
  }

  @Override
  public String encryptText(String inputText) {
    return reverseWords(inputText);
  }

  @Override
  public String decryptText(String inputText) {
    return reverseWords(inputText);
  }

  private String reverseWords(String inputText) {
    return Arrays.stream(inputText.split("\\b"))
        .map(StringBuffer::new)
        .map(StringBuffer::reverse)
        .map(StringBuffer::toString)
        .collect(Collectors.joining());
  }
}
