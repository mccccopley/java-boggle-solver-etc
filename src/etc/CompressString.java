package etc;

/**
 * Created by mccccopley on 1/19/2017.
 */
public class CompressString {
    public static String CompressString(String inputString) {
        StringBuilder compressedSB = new StringBuilder();

        int startCharIndex = 0;
        while (startCharIndex < inputString.length()) {
            int nextCharIndex = startCharIndex;
            while (nextCharIndex < inputString.length() && inputString.charAt(nextCharIndex) == inputString.charAt(startCharIndex)) {
                nextCharIndex++;
            }

            int charCount = nextCharIndex - startCharIndex;
            compressedSB.append(inputString.charAt(startCharIndex));
            compressedSB.append(charCount);

            startCharIndex = nextCharIndex;
        }

        if (inputString.length() > compressedSB.length()) {
            return compressedSB.toString();
        } else {
            return inputString;
        }
    }
}
