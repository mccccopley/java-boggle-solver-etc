package etc; /**
 * Created by mccccopley on 1/19/2017.
 */

import com.google.common.io.BaseEncoding;

public class Util {
    public static byte[] GetByteArrayFromHexString(String hex) {
        return BaseEncoding.base16().lowerCase().decode(hex.toLowerCase());
    }

    public static String GetHexStringFromByteArray(byte[] bytes) {
        return (BaseEncoding.base16().lowerCase().encode(bytes)).toUpperCase();
    }

    public static boolean AreByteArraysEqual(byte[] array1, byte[] array2) {
        int arrayLengths = array1.length;

        if (arrayLengths != array2.length) {
            return false;
        }

        for (int index = 0; index < arrayLengths; index++) {
            if (array1[index] != array2[index]) {
                return false;
            }
        }

        return true;
    }

    public static boolean AreCharArraysEqual(char[] array1, char[] array2) {
        int arrayLengths = array1.length;

        if (arrayLengths != array2.length) {
            return false;
        }

        for (int index = 0; index < arrayLengths; index++) {
            if (array1[index] != array2[index]) {
                return false;
            }
        }

        return true;
    }

    public static boolean IsSpaceChar(char ch) {
        return ch == ' ';
    }

    public static void MoveCharRight(char[] chars, int index, int count) {
        chars[index + count] = chars[index];
    }
}
