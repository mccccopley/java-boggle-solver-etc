package etc;

/**
 * Created by mccccopley on 1/22/2017.
 */
public class Mastermind {
    public static class CheckResults {
        public int numberCorrect;
        public int numberCorrectColor;

        CheckResults(int numberCorrect, int numberCorrectColor) {
            this.numberCorrect = numberCorrect;
            this.numberCorrectColor = numberCorrectColor;
        }
    }

    private static void sortCharArray(char[] chars, int offset, int length) {
        int end = offset + length;
        int last = end - 1;

        while (offset < last) {
            int currentIndex = offset;
            int minIndex = -1;
            char minElement = 127;

            while (currentIndex < end) {
                if (chars[currentIndex] < minElement) {
                    minElement = chars[currentIndex];
                    minIndex = currentIndex;
                }
                currentIndex++;
            }

            char tmp = chars[minIndex];
            chars[minIndex] = chars[offset];
            chars[offset] = tmp;

            offset++;
        }
    }

    public static CheckResults Check(char[] solution, char[] guess) {
        int numberOfSlots = solution.length;
        char[] incorrectSolutionChars = new char[numberOfSlots];
        char[] incorrectGuessChars = new char[numberOfSlots];
        int incorrectCount = 0;
        int correctCount = 0;
        for (int i = 0; i < numberOfSlots; ++i) {
            if (solution[i] == guess[i]) {
                correctCount++;
            } else {
                incorrectSolutionChars[incorrectCount] = solution[i];
                incorrectGuessChars[incorrectCount] = guess[i];
                incorrectCount++;
            }
        }

        if (correctCount == numberOfSlots) {
            return new CheckResults(correctCount, 0);
        }

        sortCharArray(incorrectSolutionChars, 0, incorrectCount);
        sortCharArray(incorrectGuessChars, 0, incorrectCount);

        int correctColorCount = 0;
        int guessIndex = 0;
        int solutionIndex = 0;
        while (guessIndex < incorrectCount && solutionIndex < incorrectCount) {
            if (incorrectSolutionChars[solutionIndex] == incorrectGuessChars[guessIndex]) {
                correctColorCount++;
                solutionIndex++;
                guessIndex++;
            } else if (incorrectGuessChars[guessIndex] < incorrectSolutionChars[solutionIndex]) {
                guessIndex++;
            } else if (incorrectGuessChars[guessIndex] > incorrectSolutionChars[solutionIndex]) {
                solutionIndex++;
            }
        }

        return new CheckResults(correctCount, correctColorCount);
    }
}
