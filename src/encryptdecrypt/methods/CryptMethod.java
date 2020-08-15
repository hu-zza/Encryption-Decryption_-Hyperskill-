package encryptdecrypt.methods;

import java.util.HashMap;

public abstract class CryptMethod
{
    private final static HashMap<String, CryptMethod> methodMap = new HashMap<>();
    final StringBuilder convertedText = new StringBuilder();
    final HashMap<String, String> argsMap = new HashMap<>();

    public abstract String encryptText(String inputText);
    public abstract String decryptText(String inputText);

    static
    {
        CryptMethodIndex.registerAllMethods();
    }

    static void register(String methodName, CryptMethod instance)
    {
        if (methodName != null && instance != null)
        {
            methodMap.put(methodName, instance);
        }
    }

    public static CryptMethod getInstance(String methodName, String... args)
    {

        if (methodMap.containsKey(methodName.toUpperCase()))
        {
            CryptMethod method = methodMap.get(methodName.toUpperCase());
            method.setArgs(args);
            return method;
        }
        return null;
    }

    private void setArgs(String... args)
    {
        argsMap.clear();
        for (int x = 0; x < args.length / 2; x++)
        {
            this.argsMap.put(
                    args[2 * x], args[2 * x + 1]
            );
        }
    }
}