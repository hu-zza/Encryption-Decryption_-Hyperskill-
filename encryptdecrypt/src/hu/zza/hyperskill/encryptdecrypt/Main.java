package hu.zza.hyperskill.encryptdecrypt;

import hu.zza.hyperskill.cryptservice.CryptService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Function;

public class Main {

  private static final Map<String, String> defaults =
      Map.of("-mode", "ENC", "-alg", "SHIFT", "-key", "0", "-data", "", "-in", "", "-out", "");
  private static final Map<String, String> settings = new HashMap<>(defaults);
  private static final ArrayList<String> inputText = new ArrayList<>();
  private static final ArrayList<String> outputText = new ArrayList<>();
  private static String[] arguments;
  private static Optional<CryptService> cryptService = Optional.empty();
  private static Function<String, String> cryptMethod = String::toString;

  public static void main(String... args) {
    arguments = args;
    initializeArguments();
    prepareInputText();
    try {
      selectService();
      configService();
      selectMethod();
      convert();
      exportOutputText();
    } catch (NoClassDefFoundError e) {
    System.err.println("Can not load any CryptService.");
  }
  }

  private static void initializeArguments() {
    for (int x = 0; x < arguments.length / 2; x++) {
      settings.put(arguments[2 * x], arguments[2 * x + 1]);
    }
  }

  private static void prepareInputText() {
    String in = settings.get("-in");
    String data = settings.get("-data");

    if (!in.isEmpty()) {
      importFromTextFile(in);
    } else if (!data.isEmpty()) {
      inputText.add(data);
    } else {
      inputText.add("");
    }
  }

  private static void selectService() {
      ServiceLoader<CryptService> loader = ServiceLoader.load(CryptService.class);
      loader.forEach(Main::setCryptServiceOptionally);
  }

  private static void setCryptServiceOptionally(CryptService service) {
    if (settings.get("-alg").equalsIgnoreCase(service.getName())) {
      cryptService = Optional.of(service);
    }
  }

  private static void configService() {
    cryptService.ifPresent(service -> service.setArguments(arguments));
  }

  private static void selectMethod() {
    if (cryptService.isPresent()) {
      CryptService service = cryptService.get();

      cryptMethod =
          "ENC".equalsIgnoreCase(settings.get("-mode"))
              ? service::encryptText
              : service::decryptText;
    }
  }

  private static void convert() {
    inputText.stream().map(cryptMethod).forEach(outputText::add);
  }

  private static void exportOutputText() {
    String out = settings.get("-out");

    if (!out.isEmpty()) {
      exportToTextFile(out);
    } else {
      outputText.forEach(System.out::println);
    }
  }

  private static void importFromTextFile(String in) {
    try {
      inputText.addAll(Files.readAllLines(Path.of(in)));
    } catch (IOException e) {
      System.err.printf("%nError while importing:%n%s%n%n", e.getMessage());
    }
  }

  private static void exportToTextFile(String out) {
    try {
      Files.write(Path.of(out), outputText);
    } catch (IOException e) {
      System.err.printf("%nError while exporting:%n%s%n%n", e.getMessage());
    }
  }
}
