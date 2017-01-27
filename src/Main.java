import boggle.IBoggleBoard;
import boggle.IWordDictIter;
import boggle.IWordDictionary;
import boggle.IWordSearcher;
import boggle.impl.BoggleBoard;
import boggle.impl.BoggleWordFinder;
import boggle.impl.TrieWordDictionary;
import com.google.common.base.Charsets;
import com.google.common.primitives.Ints;
import etc.*;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.URLify
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.URLify() ...");
        {
            char[] sourceTestUrl = "Mr  John Smith      ".toCharArray();
            char[] expectedTestOutput = "Mr%20%20John%20Smith".toCharArray();
            URLify.URLify(sourceTestUrl);
            if (!Util.AreCharArraysEqual(sourceTestUrl, expectedTestOutput)) {
                String expectedString = String.copyValueOf(expectedTestOutput);
                String resultString = String.copyValueOf(sourceTestUrl);
                System.out.format("etc.URLify() failed!!! expected: %s, got: %s\n", expectedString, resultString);
            } else {
                System.out.format("etc.URLify() succeeded!!!\n");
            }
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.CompressString
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.CompressString() ...");
        {
            String testInputString = "aabcccccaaa";
            String expectedTestOutput = "a2b1c5a3";
            String resultString = CompressString.CompressString(testInputString);
            if (!resultString.equals(expectedTestOutput)) {
                System.out.format("etc.CompressString() failed!!! expected: %s, got: %s\n", expectedTestOutput, resultString);
            } else {
                System.out.format("etc.CompressString() succeeded!!!\n");
            }
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.RotateMatrix
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.RotateMatrix() ...");
        {
            byte[] testInputMatrix = Util.GetByteArrayFromHexString("AABBCCDDDDCCBBAAEEFFAABBBBAAFFEE");
            byte[] expectedTestOutput = Util.GetByteArrayFromHexString("BBEEDDAAAAFFCCBBFFAABBCCEEBBAADD");
            RotateMatrix.RotateMatrix90CW(testInputMatrix, 4);
            if (!Util.AreByteArraysEqual(testInputMatrix, expectedTestOutput)) {
                String expectedString = Util.GetHexStringFromByteArray(expectedTestOutput);
                String resultString = Util.GetHexStringFromByteArray(testInputMatrix);
                System.out.format("etc.RotateMatrix() failed!!! expected: %s, got: %s\n", expectedString, resultString);
            } else {
                System.out.format("etc.RotateMatrix() succeeded!!!\n");
            }
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.IsBinarySearchTree
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.IsBinarySearchTree() ...");
        {
            int[] testValuesNotBST = new int[]{ 10, 5, 12, 3, 6, 11, 17, 1, 4, 9 };
            IsBinarySearchTree.BinaryTreeNode treeNotBST = IsBinarySearchTree.BinaryTreeNode.MakeTreeFromArray(testValuesNotBST);
            if (IsBinarySearchTree.IsBinarySearchTree_V1(treeNotBST)) {
                System.out.println("IsBinarySearchTree_V1() ... failed on treeNotBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V1() ... succeeded on treeNotBST!!!");
            }
            if (IsBinarySearchTree.IsBinarySearchTree_V2(treeNotBST)) {
                System.out.println("IsBinarySearchTree_V2() ... failed on treeNotBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V2() ... succeeded on treeNotBST!!!");
            }
            if (IsBinarySearchTree.IsBinarySearchTree_V2_Iterative(treeNotBST)) {
                System.out.println("IsBinarySearchTree_V2_Iterative() ... failed on treeNotBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V2_Iterative() ... succeeded on treeNotBST!!!");
            }

            int[] testValuesIsBST = new int[]{ 10, 5, 12, 3, 9, 11, 17, 1, 4, 6 };
            IsBinarySearchTree.BinaryTreeNode treeIsBST = IsBinarySearchTree.BinaryTreeNode.MakeTreeFromArray(testValuesIsBST);
            if (!IsBinarySearchTree.IsBinarySearchTree_V1(treeIsBST)) {
                System.out.println("IsBinarySearchTree_V1() ... failed on treeIsBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V1() ... succeeded on treeIsBST!!!");
            }
            if (!IsBinarySearchTree.IsBinarySearchTree_V2(treeIsBST)) {
                System.out.println("IsBinarySearchTree_V2() ... failed on treeIsBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V2() ... succeeded on treeIsBST!!!");
            }
            if (!IsBinarySearchTree.IsBinarySearchTree_V2_Iterative(treeIsBST)) {
                System.out.println("IsBinarySearchTree_V2_Iterative() ... failed on treeIsBST!!!");
            } else {
                System.out.println("IsBinarySearchTree_V2_Iterative() ... succeeded on treeIsBST!!!");
            }

            int[] remadeTreeValuesNotBST = Ints.toArray(IsBinarySearchTree.BinaryTreeNode.MakeArrayFromTree(treeNotBST));
            int[] remadeTreeValuesIsBST = Ints.toArray(IsBinarySearchTree.BinaryTreeNode.MakeArrayFromTree(treeIsBST));
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST GetDependencyOrder
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST GetDependencyOrder() ...");
        {
            int[] testIds = new int[]{ 1, 2, 3, 4, 5, 6 };
            Dependencies.Dependency[] testDependencies = new Dependencies.Dependency[]{
                    new Dependencies.Dependency(4, 1),
                    new Dependencies.Dependency(2, 6),
                    new Dependencies.Dependency(4, 2),
                    new Dependencies.Dependency(1, 6),
                    new Dependencies.Dependency(3, 4),
            };
            List<Integer> dependencyOrder = Dependencies.GetDependencyOrder(testIds, testDependencies);
            int[] resultOrderArray = Ints.toArray(dependencyOrder);
            int[] expectedTestOutput = new int[]{ 6, 5, 1, 2, 4, 3 };
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.EnglishInt
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.EnglishInt() ...");
        {
            int testNumber1 = -4092;
            String testOutput1 = EnglishInt.Englishify(testNumber1);
            int testNumber2 = 100370015;
            String testOutput2 = EnglishInt.Englishify(testNumber2);
            int testNumber3 = -15407;
            String testOutput3 = EnglishInt.Englishify(testNumber3);
            int testNumber4 = 1017019512;
            String testOutput4 = EnglishInt.Englishify(testNumber4);

            String expectedOutput1 = "negative four thousand, ninety-two";
            String expectedOutput2 = "one hundred million, three hundred seventy thousand, fifteen";
            String expectedOutput3 = "negative fifteen thousand, four hundred seven";
            String expectedOutput4 = "one billion, seventeen million, nineteen thousand, five hundred twelve";
            int foo = 1;
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.Mastermind
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.Mastermind() ...");
        {
            char[] testSolution = new char[]{ 'R', 'G', 'B', 'Y' };
            char[] testGuess = new char[]{ 'G', 'G', 'R', 'R' };
            Mastermind.CheckResults results = Mastermind.Check(testSolution, testGuess);
            System.out.format("We found [%d] correct colors and slots, and [%d] correct colors.\n", results.numberCorrect, results.numberCorrectColor);
            int foo = 1;
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST Boggle
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        Path dictionaryFilePath = FileSystems.getDefault().getPath(".", "boggle_dictionary.txt");

        try {
            // BUILD THE DICTIONARY

            List<String> dictionaryLines = Files.readAllLines(dictionaryFilePath, Charsets.UTF_8);
            IWordDictionary dictionary = new TrieWordDictionary();
            System.out.format("Loading words from the dictionary file...\n");
            for (String line : dictionaryLines) {
                dictionary.InsertWord(line);
                System.out.format("...Loaded word: [%s].\n", line);
            }

            IWordDictIter dictIter = dictionary.GetIterator();
            System.out.format("Traversing words in the dictionary...\n");
            while (dictIter.HasNext()) {
                String word = dictIter.GetNext();
                System.out.format("...Traversed word: [%s].\n", word);
            }

            // BUILD THE BOARD

            char[] boggleBoardLetters = new char[]{
                    'P', 'E', 'R', 'Z',
                    'P', 'L', 'T', 'I',
                    'C', 'A', 'M', 'X',
                    'D', 'F', 'K', 'N'
            };

            IBoggleBoard boggleBoard = new BoggleBoard();
            boggleBoard.Initialize(boggleBoardLetters, 4);

            ArrayList<String> foundWords = new ArrayList<>();
            IWordSearcher boggleSearcher = new BoggleWordFinder();
            boggleSearcher.FindWords(dictionary, boggleBoard, foundWords);

            System.out.println("The words found on the boggle board are:");
            for (String foundWord : foundWords) {
                System.out.println(foundWord);
            }

            int foo = 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        ///////////////////////////////////////////////////////////////////////
        int endOfTesting = 1;
    }
}
