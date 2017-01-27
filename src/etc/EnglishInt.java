package etc;

/**
 * Created by mccccopley on 1/22/2017.
 */
public class EnglishInt {
    private static String[] suffixesByThreeDigits = new String[]{ "", "thousand", "million", "billion" };
    private static String[] digitNames = new String[]{ "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    private static String zeroName = "zero";
    private static String negativeName = "negative";
    private static String[] tenToNineteenNames = new String[]{ "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    private static String[] tensDigitNames = new String[]{ "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
    private static String hundredName = "hundred";
    private static char Space = ' ';
    private static char Dash = '-';
    private static char Comma = ',';

    public static String Englishify(int number) {
        StringBuilder stringBuilder = new StringBuilder();

        // case 1: zero
        if (number == 0) {
            return zeroName;
        }

        // case 2: all the rest
        else {
            boolean insertedEnglishPreviously = false;

            int absNumber = (number < 0 ? - number : number);

            // group into three digit clusters
            int currentSuffixIndex = 0;
            while (absNumber != 0) {
                StringBuilder threeDigitCluster = new StringBuilder();

                int ones = ( absNumber % 10 );
                int tens = ( absNumber % 100 ) - ones;
                int hundreds = ( absNumber % 1000 ) - tens - ones;

                tens /= 10;
                hundreds /= 100;

                // handle hundreds?
                if (hundreds != 0) {
                    threeDigitCluster.append(digitNames[hundreds]);
                    threeDigitCluster.append(Space);
                    threeDigitCluster.append(hundredName);

                    if( tens != 0 || ones != 0) {
                        threeDigitCluster.append(Space);
                    }
                }

                // case 1: three digits are between 10 and 19
                if (tens == 1) {
                    threeDigitCluster.append(tenToNineteenNames[ones]);
                }

                // case 2: three digits are between 20 and 99
                else if (tens > 1) {
                    threeDigitCluster.append(tensDigitNames[tens]);

                    if (ones != 0) {
                        threeDigitCluster.append(Dash);
                        threeDigitCluster.append(digitNames[ones]);
                    }
                }

                // case 3: there are only ones
                else {
                    threeDigitCluster.append(digitNames[ones]);
                }

                // finally append the appropriate suffix
                if (currentSuffixIndex != 0) {
                    threeDigitCluster.append(Space);
                    threeDigitCluster.append(suffixesByThreeDigits[currentSuffixIndex]);
                }

                currentSuffixIndex++;
                absNumber = absNumber / 1000;

                if (insertedEnglishPreviously) {
                    threeDigitCluster.append(Comma);
                    threeDigitCluster.append(Space);
                }

                stringBuilder.insert(0, threeDigitCluster.toString());

                insertedEnglishPreviously |= (ones != 0 || tens != 0 || hundreds != 0);
            }

            // is negative?
            if (number < 0) {
                stringBuilder.insert(0, Space);
                stringBuilder.insert(0, negativeName);
            }
        }

        return stringBuilder.toString();
    }
}
