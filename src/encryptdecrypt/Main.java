package encryptdecrypt;

import encryptdecrypt.methods.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final HashMap<String, String> argsMap = new HashMap<>();
    private static final ArrayList<String> inputText = new ArrayList<>();
    private static final ArrayList<String> outputText = new ArrayList<>();
    private static CryptMethod method;
    private static String mode;
    private static String alg;
    private static int key;
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
            argsMap.put(args[2 * x], args[2 * x + 1]);
        }

        mode = argsMap.getOrDefault("-mode", "enc");
        alg = argsMap.getOrDefault("-alg", "shift");
        key = Integer.parseInt(argsMap.getOrDefault("-key", "0"));
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
                System.out.printf("%nError:%n%s%n%n", e.getMessage());
            }
        } else if (!data.isEmpty()) {
            inputText.add(data);
        } else {
            inputText.add("");
        }
    }

    private static void selectMethod() {
        switch (alg.toUpperCase()) {
            case "SHIFT":
                method = new ShiftLatin(mode, key);
                break;

            case "UNICODE":
                method = new ShiftUnicode(mode, key);
                break;
        }
    }

    private static void convert() {
        for (String s : inputText) {
            outputText.add(method.convertText(s));
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
                System.out.printf("%nError:%n%s%n%n", e.getMessage());
            }
        } else {
            for (String s : outputText) {
                System.out.println(s);
            }
        }
    }
}