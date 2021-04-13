package hu.zza.hyperskill.cryptservice;

public interface CryptService {
  String getName();

  String getDescription();

  void setArguments(String... arguments);

  String encryptText(String inputText);

  String decryptText(String inputText);
}
