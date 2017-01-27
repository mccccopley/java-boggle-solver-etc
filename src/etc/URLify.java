package etc;

/**
 * Created by mccccopley on 1/19/2017.
 */
public class URLify {
    public static void URLify(char[] urlChars) {
        int urlLength = urlChars.length;
        int endOfUrl = urlLength - 1;
        while (endOfUrl >= 0) {
            if (Util.IsSpaceChar(urlChars[endOfUrl])) {
                endOfUrl--;
            } else {
                break;
            }
        }

        int numSpaces = 0;
        int currUrlIndex = endOfUrl;
        while (currUrlIndex >= 0) {
            if (Util.IsSpaceChar(urlChars[currUrlIndex])) {
                numSpaces++;
            }
            currUrlIndex--;
        }

        while (numSpaces > 0) {
            while (!Util.IsSpaceChar(urlChars[endOfUrl])) {
                Util.MoveCharRight(urlChars, endOfUrl, 2 * numSpaces);
                endOfUrl--;
            }

            numSpaces--;
            int replaceIndex = endOfUrl + (2 * numSpaces);

            urlChars[replaceIndex] = '%';
            urlChars[replaceIndex + 1] = '2';
            urlChars[replaceIndex + 2] = '0';

            endOfUrl--;
        }
    }
}
