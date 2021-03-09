package encryptdecrypt;

import encryptdecrypt.methods.CryptMethod;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

  private static final HashMap<String, String> argsMap = new HashMap<>();
  private static final ArrayList<String> inputText = new ArrayList<>();
  private static final ArrayList<String> outputText = new ArrayList<>();
  private static CryptMethod method;
  private static String alg;
  private static String mode;
  private static String data;
  private static String in;
  private static String out;


  public static void main(String[] args) {
    initializeArguments(args);
    prepareInputText();
    selectMethod();
    convert();
    exportOutputText();
  }

  private static void initializeArguments(String[] args) {
    for (int x = 0; x < args.length / 2; x++) {
      argsMap.put(
          args[2 * x], args[2 * x + 1]
      );
    }

    /////////////////////////////////////////////
    //  Because of the backward compatibility
    //  with the original Hyperskill.org project.
    if (!argsMap.containsKey("-arg:key")) {
      argsMap.put(
          "-arg:key", argsMap.getOrDefault("-key", "0")
      );
    }
    /////////////////////////////////////////////

    alg = argsMap.getOrDefault("-alg", "SHIFT");
    mode = argsMap.getOrDefault("-mode", "ENC");
    data = argsMap.getOrDefault("-data", "");
    in = argsMap.getOrDefault("-in", "");
    out = argsMap.getOrDefault("-out", "");
  }

  private static void prepareInputText() {
    if (!in.isEmpty()) {
      try (var br = new BufferedReader(new FileReader(in))) {
        String line;
        while ((line = br.readLine()) != null) {
          inputText.add(line);
        }
      } catch (IOException e) {
        System.out.printf("%nError while importing:%n%s%n%n", e.getMessage());
      }
    } else if (!data.isEmpty()) {
      inputText.add(data);
    } else {
      inputText.add("");
    }
  }

  private static void selectMethod() {
    ArrayList<String> args = new ArrayList<>();
    for (Map.Entry<String, String> entry : argsMap.entrySet()) {
      if (entry.getKey().startsWith("-arg:")) {
        args.add(entry.getKey().substring(5));
        args.add(entry.getValue());
      }
    }
    method = CryptMethod.getInstance(
        alg, args.toArray(new String[0])
    );
  }

  private static void convert() {
    if (method == null) {
      System.out.println("Set a method before the conversion.");
    } else {
      for (String s : inputText) {
        if ("ENC".equalsIgnoreCase(mode)) {
          outputText.add(method.encryptText(s));
        } else {
          outputText.add(method.decryptText(s));
        }
      }
    }
  }

  private static void exportOutputText() {
    if (!out.isEmpty()) {
      try (var bw = new BufferedWriter(new FileWriter(out))) {
        bw.write(outputText.remove(0));
        for (String s : outputText) {
          bw.newLine();
          bw.write(s);
        }
      } catch (IOException e) {
        System.out.printf("%nError while exporting:%n%s%n%n", e.getMessage());
      }
    } else {
      for (String s : outputText) {
        System.out.println(s);
      }
    }
  }
}