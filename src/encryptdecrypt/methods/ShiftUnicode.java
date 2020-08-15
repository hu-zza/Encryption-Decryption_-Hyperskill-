package encryptdecrypt.methods;

class ShiftUnicode extends CryptMethod
{
    private final int key = Integer.parseInt(argsMap.getOrDefault("key", "0"));

    @Override
    public String encryptText(String inputText)
    {
        return convertText(inputText, true);
    }

    @Override
    public String decryptText(String inputText)
    {
        return convertText(inputText, false);
    }

    private String convertText(String inputText, boolean encryption)
    {
        convertedText.setLength(0);

        for (char ch : inputText.toCharArray())
        {
            convertedText.append(
                    (char) (encryption ? ch + key : ch - key)
            );
        }
        return convertedText.toString();
    }
}