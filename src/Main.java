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
import java.util.Scanner;

public class Main {
    public static void sortValues(long[] values) {
        int numValues = values.length;
        int halfway = numValues / 2;
        for (int outerIndex = 0; outerIndex < halfway; ++outerIndex) {
            long minValue = Long.MAX_VALUE;
            long maxValue = Long.MIN_VALUE;
            int minValueIndex = -1;
            int maxValueIndex = -1;
            int endingIndex = numValues - outerIndex;
            for (int innerIndex = outerIndex; innerIndex < endingIndex; ++innerIndex) {
                if (values[innerIndex] < minValue) {
                    minValueIndex = innerIndex;
                    minValue = values[innerIndex];
                }
                if (values[innerIndex] > maxValue) {
                    maxValueIndex = innerIndex;
                    maxValue = values[innerIndex];
                }
            }

            long tmp1 = values[outerIndex];
            values[outerIndex] = minValue;
            values[minValueIndex] = tmp1;

            if (maxValueIndex == outerIndex) {
                maxValueIndex = minValueIndex;
            }

            long tmp2 = values[endingIndex - 1];
            values[endingIndex - 1] = maxValue;
            values[maxValueIndex] = tmp2;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        long[] values = new long[]{539674108, 549382170, 270968351, 746219035, 140597628};
        sortValues(values);

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.Heap
        ///////////////////////////////////////////////////////////////////////
        {
            Heap heap = new Heap();
            Scanner scanner = new Scanner(TEST_INPUT);
            int n = scanner.nextInt();
            for (int query = 0; query < n; ++query) {
                int queryType = scanner.nextInt();
                if (queryType == 1) {
                    int value = scanner.nextInt();
                    heap.addToHeap(value);
                } else if (queryType == 2) {
                    int value = scanner.nextInt();
                    heap.removeFromHeap(value);
                } else if (queryType == 3) {
                    heap.printMin();
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.CountOccurences
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.CountOccurences() ...");
        {
            String[] array = new String[]{
                    "lekgdisnsbfdzpqlkg",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "urcadmrwlqe",
                    "mpgqsvxrijpombyv",
                    "mpgqsvxrijpombyv",
                    "urcadmrwlqe",
                    "mpgqsvxrijpombyv",
                    "eagemhdygyv",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "urcadmrwlqe",
                    "jwvwwnrhuai",
                    "kvugevicpsdf",
                    "kvugevicpsdf",
                    "mpgqsvxrijpombyv",
                    "urcadmrwlqe",
                    "mpgqsvxrijpombyv",
                    "exdafbnobg",
                    "qhootohpnfvbl",
                    "suffrbmqgnln",
                    "exdafbnobg",
                    "exdafbnobg",
                    "eagemhdygyv",
                    "mpgqsvxrijpombyv",
                    "urcadmrwlqe",
                    "jwvwwnrhuai",
                    "lekgdisnsbfdzpqlkg",
                    "mpgqsvxrijpombyv",
                    "lekgdisnsbfdzpqlkg",
                    "jwvwwnrhuai",
                    "exdafbnobg",
                    "mpgqsvxrijpombyv",
                    "kvugevicpsdf",
                    "qhootohpnfvbl",
                    "urcadmrwlqe",
                    "kvugevicpsdf",
                    "mpgqsvxrijpombyv",
                    "lekgdisnsbfdzpqlkg",
                    "mpgqsvxrijpombyv",
                    "kvugevicpsdf",
                    "qhootohpnfvbl",
                    "lxyqetmgdbmh",
                    "urcadmrwlqe",
                    "urcadmrwlqe",
                    "kvugevicpsdf",
                    "lxyqetmgdbmh",
                    "urcadmrwlqe",
                    "lxyqetmgdbmh",
                    "jwvwwnrhuai",
                    "qhootohpnfvbl",
                    "qhootohpnfvbl",
                    "jwvwwnrhuai",
                    "lekgdisnsbfdzpqlkg",
                    "kvugevicpsdf",
                    "mpgqsvxrijpombyv",
                    "exdafbnobg",
                    "kvugevicpsdf",
                    "lekgdisnsbfdzpqlkg",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "mpgqsvxrijpombyv",
                    "suffrbmqgnln",
                    "mpgqsvxrijpombyv",
                    "qhootohpnfvbl",
                    "jwvwwnrhuai",
                    "mpgqsvxrijpombyv",
                    "qhootohpnfvbl",
                    "lekgdisnsbfdzpqlkg",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "kvugevicpsdf",
                    "eagemhdygyv",
                    "eagemhdygyv",
                    "lxyqetmgdbmh",
                    "qhootohpnfvbl",
                    "lxyqetmgdbmh",
                    "exdafbnobg",
                    "qhootohpnfvbl",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "suffrbmqgnln",
                    "mpgqsvxrijpombyv",
                    "urcadmrwlqe",
                    "eagemhdygyv",
                    "lxyqetmgdbmh",
                    "urcadmrwlqe",
                    "suffrbmqgnln",
                    "qhootohpnfvbl",
                    "kvugevicpsdf",
                    "lekgdisnsbfdzpqlkg",
                    "lxyqetmgdbmh",
                    "mpgqsvxrijpombyv",
                    "jwvwwnrhuai",
                    "lxyqetmgdbmh",
                    "lxyqetmgdbmh",
                    "lekgdisnsbfdzpqlkg",
                    "qhootohpnfvbl",
            };
            String[] queries = new String[]{
                    "exdafbnobg",
                    "eagemhdygyv",
                    "mpgqsvxrijpombyv",
                    "kvugevicpsdf",
                    "lekgdisnsbfdzpqlkg",
                    "kvugevicpsdf",
                    "exdafbnobg",
                    "qhootohpnfvbl",
                    "eagemhdygyv",
                    "kvugevicpsdf",
                    "suffrbmqgnln",
                    "jwvwwnrhuai",
                    "lekgdisnsbfdzpqlkg",
                    "lekgdisnsbfdzpqlkg",
                    "mpgqsvxrijpombyv",
                    "jwvwwnrhuai",
                    "kvugevicpsdf",
                    "lekgdisnsbfdzpqlkg",
                    "exdafbnobg",
                    "suffrbmqgnln",
                    "qhootohpnfvbl",
                    "eagemhdygyv",
                    "exdafbnobg",
                    "suffrbmqgnln",
                    "jwvwwnrhuai",
                    "qhootohpnfvbl",
                    "eagemhdygyv",
                    "exdafbnobg",
                    "exdafbnobg",
                    "jwvwwnrhuai",
                    "qhootohpnfvbl",
                    "lxyqetmgdbmh",
                    "qhootohpnfvbl",
                    "suffrbmqgnln",
                    "lxyqetmgdbmh",
                    "qhootohpnfvbl",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "eagemhdygyv",
                    "qhootohpnfvbl",
                    "mpgqsvxrijpombyv",
                    "qhootohpnfvbl",
                    "jwvwwnrhuai",
                    "exdafbnobg",
                    "eagemhdygyv",
                    "eagemhdygyv",
                    "kvugevicpsdf",
                    "kvugevicpsdf",
                    "jwvwwnrhuai",
                    "urcadmrwlqe",
                    "lxyqetmgdbmh",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "exdafbnobg",
                    "eagemhdygyv",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "exdafbnobg",
                    "lekgdisnsbfdzpqlkg",
                    "jwvwwnrhuai",
                    "eagemhdygyv",
                    "urcadmrwlqe",
                    "kvugevicpsdf",
                    "lekgdisnsbfdzpqlkg",
                    "jwvwwnrhuai",
                    "eagemhdygyv",
                    "lekgdisnsbfdzpqlkg",
                    "exdafbnobg",
                    "kvugevicpsdf",
                    "jwvwwnrhuai",
                    "exdafbnobg",
                    "lxyqetmgdbmh",
                    "exdafbnobg",
                    "lxyqetmgdbmh",
                    "jwvwwnrhuai",
                    "mpgqsvxrijpombyv",
                    "eagemhdygyv",
                    "urcadmrwlqe",
                    "kvugevicpsdf",
                    "qhootohpnfvbl",
                    "jwvwwnrhuai",
                    "eagemhdygyv",
                    "urcadmrwlqe",
                    "urcadmrwlqe",
                    "exdafbnobg",
                    "qhootohpnfvbl",
                    "exdafbnobg",
                    "eagemhdygyv",
                    "exdafbnobg",
                    "jwvwwnrhuai",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "mpgqsvxrijpombyv",
                    "urcadmrwlqe",
                    "urcadmrwlqe",
                    "eagemhdygyv",
                    "eagemhdygyv",
                    "jwvwwnrhuai",
                    "suffrbmqgnln",
                    "eagemhdygyv",
            };
            CountOccurences.countOccurences(array, queries);
        }

        ///////////////////////////////////////////////////////////////////////
        // TEST etc.PowerCompany
        ///////////////////////////////////////////////////////////////////////
        System.out.println();
        System.out.println("TEST etc.PowerCompany() ...");
        {
            int[] towerArray = new int[]{ 0, 1, 1, 1, 1, 0};
            PowerCompany.calcMinimumTowerCount(towerArray, 2);
        }

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

    static String TEST_INPUT =
            "1000\n" +
                    "1 189076\n" +
                    "2 189076\n" +
                    "1 193865\n" +
                    "2 193865\n" +
                    "1 -419921\n" +
                    "1 429676\n" +
                    "3\n" +
                    "2 429676\n" +
                    "3\n" +
                    "1 21716\n" +
                    "1 551843\n" +
                    "1 950119\n" +
                    "1 63171\n" +
                    "3\n" +
                    "1 841804\n" +
                    "1 170054\n" +
                    "1 835419\n" +
                    "2 835419\n" +
                    "2 950119\n" +
                    "3\n" +
                    "1 258308\n" +
                    "1 -734231\n" +
                    "1 569347\n" +
                    "1 52941\n" +
                    "1 777770\n" +
                    "2 -734231\n" +
                    "1 355316\n" +
                    "3\n" +
                    "1 415025\n" +
                    "1 754479\n" +
                    "2 777770\n" +
                    "1 -744898\n" +
                    "2 551843\n" +
                    "1 509662\n" +
                    "3\n" +
                    "3\n" +
                    "1 765746\n" +
                    "3\n" +
                    "1 809282\n" +
                    "2 -744898\n" +
                    "3\n" +
                    "1 367727\n" +
                    "3\n" +
                    "3\n" +
                    "2 809282\n" +
                    "3\n" +
                    "2 52941\n" +
                    "3\n" +
                    "1 246500\n" +
                    "2 63171\n" +
                    "2 -419921\n" +
                    "1 369292\n" +
                    "1 897961\n" +
                    "2 21716\n" +
                    "2 569347\n" +
                    "1 91419\n" +
                    "1 175735\n" +
                    "1 51897\n" +
                    "2 841804\n" +
                    "3\n" +
                    "3\n" +
                    "1 54459\n" +
                    "3\n" +
                    "2 51897\n" +
                    "3\n" +
                    "3\n" +
                    "1 842004\n" +
                    "1 705665\n" +
                    "3\n" +
                    "1 943329\n" +
                    "1 909673\n" +
                    "3\n" +
                    "3\n" +
                    "1 305472\n" +
                    "3\n" +
                    "1 110230\n" +
                    "1 709523\n" +
                    "1 449825\n" +
                    "1 997699\n" +
                    "3\n" +
                    "2 509662\n" +
                    "2 110230\n" +
                    "3\n" +
                    "1 341004\n" +
                    "1 760694\n" +
                    "3\n" +
                    "1 585977\n" +
                    "1 590699\n" +
                    "2 765746\n" +
                    "1 850310\n" +
                    "1 329713\n" +
                    "1 293617\n" +
                    "2 705665\n" +
                    "2 369292\n" +
                    "1 335461\n" +
                    "3\n" +
                    "2 355316\n" +
                    "3\n" +
                    "2 246500\n" +
                    "3\n" +
                    "1 636216\n" +
                    "1 -200964\n" +
                    "3\n" +
                    "2 329713\n" +
                    "1 963785\n" +
                    "2 943329\n" +
                    "1 536896\n" +
                    "2 754479\n" +
                    "1 292290\n" +
                    "1 829183\n" +
                    "1 580849\n" +
                    "1 668512\n" +
                    "2 850310\n" +
                    "1 439715\n" +
                    "1 405721\n" +
                    "1 246013\n" +
                    "1 224404\n" +
                    "3\n" +
                    "3\n" +
                    "2 897961\n" +
                    "1 471576\n" +
                    "3\n" +
                    "1 -981246\n" +
                    "1 684795\n" +
                    "3\n" +
                    "3\n" +
                    "2 585977\n" +
                    "1 313281\n" +
                    "3\n" +
                    "1 -556695\n" +
                    "3\n" +
                    "1 758174\n" +
                    "2 91419\n" +
                    "1 782464\n" +
                    "1 -432541\n" +
                    "3\n" +
                    "3\n" +
                    "2 684795\n" +
                    "1 555219\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "1 179580\n" +
                    "1 594124\n" +
                    "2 471576\n" +
                    "1 442463\n" +
                    "3\n" +
                    "1 757281\n" +
                    "3\n" +
                    "1 -350397\n" +
                    "2 758174\n" +
                    "3\n" +
                    "1 803700\n" +
                    "1 888356\n" +
                    "2 415025\n" +
                    "3\n" +
                    "1 110115\n" +
                    "2 367727\n" +
                    "1 565449\n" +
                    "1 580414\n" +
                    "1 485262\n" +
                    "2 -556695\n" +
                    "1 32109\n" +
                    "1 884510\n" +
                    "3\n" +
                    "1 151595\n" +
                    "3\n" +
                    "1 474791\n" +
                    "1 864073\n" +
                    "1 419158\n" +
                    "1 -525578\n" +
                    "1 832351\n" +
                    "1 779947\n" +
                    "3\n" +
                    "1 745858\n" +
                    "3\n" +
                    "1 570336\n" +
                    "2 151595\n" +
                    "1 981304\n" +
                    "3\n" +
                    "3\n" +
                    "1 894251\n" +
                    "2 485262\n" +
                    "1 935621\n" +
                    "2 341004\n" +
                    "1 759119\n" +
                    "1 605532\n" +
                    "1 550228\n" +
                    "2 888356\n" +
                    "2 759119\n" +
                    "3\n" +
                    "3\n" +
                    "2 258308\n" +
                    "1 7950\n" +
                    "1 927079\n" +
                    "2 590699\n" +
                    "2 779947\n" +
                    "1 414425\n" +
                    "3\n" +
                    "1 683353\n" +
                    "3\n" +
                    "1 230153\n" +
                    "1 287789\n" +
                    "3\n" +
                    "1 647075\n" +
                    "3\n" +
                    "3\n" +
                    "2 927079\n" +
                    "1 169137\n" +
                    "1 141121\n" +
                    "1 731692\n" +
                    "3\n" +
                    "3\n" +
                    "2 246013\n" +
                    "3\n" +
                    "2 110115\n" +
                    "1 95953\n" +
                    "3\n" +
                    "1 584999\n" +
                    "2 757281\n" +
                    "1 85257\n" +
                    "2 963785\n" +
                    "2 580849\n" +
                    "1 744783\n" +
                    "1 304840\n" +
                    "2 580414\n" +
                    "1 900119\n" +
                    "1 95977\n" +
                    "3\n" +
                    "1 -318550\n" +
                    "1 975122\n" +
                    "1 316709\n" +
                    "3\n" +
                    "1 46919\n" +
                    "2 293617\n" +
                    "2 842004\n" +
                    "2 -350397\n" +
                    "2 570336\n" +
                    "1 431530\n" +
                    "1 383159\n" +
                    "2 224404\n" +
                    "2 54459\n" +
                    "1 233433\n" +
                    "2 636216\n" +
                    "3\n" +
                    "3\n" +
                    "1 98946\n" +
                    "2 98946\n" +
                    "2 442463\n" +
                    "1 934548\n" +
                    "2 744783\n" +
                    "1 379408\n" +
                    "2 565449\n" +
                    "1 172933\n" +
                    "1 902675\n" +
                    "3\n" +
                    "1 406271\n" +
                    "1 859885\n" +
                    "1 831350\n" +
                    "1 -424521\n" +
                    "1 414765\n" +
                    "1 816980\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 406271\n" +
                    "3\n" +
                    "3\n" +
                    "1 823113\n" +
                    "2 909673\n" +
                    "2 7950\n" +
                    "3\n" +
                    "2 859885\n" +
                    "3\n" +
                    "2 474791\n" +
                    "1 391789\n" +
                    "3\n" +
                    "2 313281\n" +
                    "2 175735\n" +
                    "3\n" +
                    "1 844265\n" +
                    "2 832351\n" +
                    "1 391095\n" +
                    "3\n" +
                    "1 665458\n" +
                    "1 -42207\n" +
                    "1 314643\n" +
                    "2 -318550\n" +
                    "1 -285756\n" +
                    "2 665458\n" +
                    "1 799778\n" +
                    "2 584999\n" +
                    "1 432206\n" +
                    "2 414425\n" +
                    "1 -547247\n" +
                    "3\n" +
                    "1 594407\n" +
                    "1 989305\n" +
                    "2 316709\n" +
                    "1 427519\n" +
                    "2 414765\n" +
                    "2 989305\n" +
                    "2 179580\n" +
                    "2 85257\n" +
                    "1 952116\n" +
                    "2 799778\n" +
                    "1 944530\n" +
                    "1 944634\n" +
                    "2 900119\n" +
                    "2 536896\n" +
                    "1 345850\n" +
                    "1 207363\n" +
                    "2 829183\n" +
                    "2 934548\n" +
                    "2 745858\n" +
                    "2 935621\n" +
                    "1 946458\n" +
                    "2 391789\n" +
                    "1 47507\n" +
                    "1 906542\n" +
                    "2 -432541\n" +
                    "3\n" +
                    "3\n" +
                    "2 594124\n" +
                    "3\n" +
                    "1 903097\n" +
                    "2 432206\n" +
                    "1 925993\n" +
                    "3\n" +
                    "2 550228\n" +
                    "2 287789\n" +
                    "2 782464\n" +
                    "3\n" +
                    "1 630573\n" +
                    "2 230153\n" +
                    "1 439517\n" +
                    "1 724986\n" +
                    "2 894251\n" +
                    "1 694680\n" +
                    "1 739122\n" +
                    "3\n" +
                    "2 709523\n" +
                    "2 46919\n" +
                    "1 56270\n" +
                    "1 553952\n" +
                    "3\n" +
                    "2 304840\n" +
                    "1 606109\n" +
                    "1 450773\n" +
                    "3\n" +
                    "1 331922\n" +
                    "1 965731\n" +
                    "1 806736\n" +
                    "3\n" +
                    "1 498132\n" +
                    "1 -643643\n" +
                    "2 816980\n" +
                    "1 363391\n" +
                    "2 997699\n" +
                    "1 753863\n" +
                    "2 391095\n" +
                    "1 487962\n" +
                    "2 906542\n" +
                    "1 237130\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 884510\n" +
                    "2 305472\n" +
                    "3\n" +
                    "1 61715\n" +
                    "1 987164\n" +
                    "2 233433\n" +
                    "1 -172643\n" +
                    "1 50592\n" +
                    "3\n" +
                    "2 739122\n" +
                    "1 713261\n" +
                    "2 753863\n" +
                    "3\n" +
                    "1 714393\n" +
                    "2 172933\n" +
                    "2 647075\n" +
                    "1 689696\n" +
                    "2 981304\n" +
                    "3\n" +
                    "1 16727\n" +
                    "2 32109\n" +
                    "2 823113\n" +
                    "2 -200964\n" +
                    "3\n" +
                    "1 614139\n" +
                    "3\n" +
                    "2 -285756\n" +
                    "2 292290\n" +
                    "2 170054\n" +
                    "1 -950756\n" +
                    "1 792906\n" +
                    "1 814158\n" +
                    "2 47507\n" +
                    "3\n" +
                    "3\n" +
                    "1 143618\n" +
                    "2 792906\n" +
                    "3\n" +
                    "1 761384\n" +
                    "3\n" +
                    "2 -643643\n" +
                    "1 496513\n" +
                    "2 902675\n" +
                    "3\n" +
                    "3\n" +
                    "1 957592\n" +
                    "1 595858\n" +
                    "3\n" +
                    "1 -118210\n" +
                    "2 595858\n" +
                    "1 618341\n" +
                    "2 335461\n" +
                    "3\n" +
                    "2 683353\n" +
                    "1 219414\n" +
                    "2 439715\n" +
                    "1 287098\n" +
                    "2 363391\n" +
                    "1 864425\n" +
                    "1 253449\n" +
                    "3\n" +
                    "2 -172643\n" +
                    "1 508805\n" +
                    "2 713261\n" +
                    "2 61715\n" +
                    "3\n" +
                    "1 237993\n" +
                    "2 143618\n" +
                    "1 565015\n" +
                    "1 274648\n" +
                    "1 107701\n" +
                    "1 450219\n" +
                    "1 222744\n" +
                    "1 105448\n" +
                    "2 496513\n" +
                    "1 826825\n" +
                    "1 215893\n" +
                    "1 264528\n" +
                    "3\n" +
                    "1 494532\n" +
                    "2 345850\n" +
                    "3\n" +
                    "1 786353\n" +
                    "1 458088\n" +
                    "1 769888\n" +
                    "3\n" +
                    "1 327085\n" +
                    "1 301653\n" +
                    "1 699753\n" +
                    "1 378244\n" +
                    "1 220134\n" +
                    "1 924463\n" +
                    "2 694680\n" +
                    "1 350819\n" +
                    "1 196149\n" +
                    "3\n" +
                    "1 677289\n" +
                    "1 -494886\n" +
                    "1 117368\n" +
                    "1 960949\n" +
                    "2 449825\n" +
                    "2 -118210\n" +
                    "3\n" +
                    "1 302153\n" +
                    "1 973995\n" +
                    "2 -42207\n" +
                    "2 431530\n" +
                    "1 434508\n" +
                    "1 -926919\n" +
                    "2 405721\n" +
                    "1 879338\n" +
                    "2 973995\n" +
                    "1 232462\n" +
                    "1 316937\n" +
                    "2 864425\n" +
                    "1 534492\n" +
                    "2 219414\n" +
                    "1 299477\n" +
                    "1 -869545\n" +
                    "2 498132\n" +
                    "3\n" +
                    "1 59882\n" +
                    "1 651443\n" +
                    "1 67458\n" +
                    "1 552095\n" +
                    "1 875288\n" +
                    "1 276239\n" +
                    "1 836464\n" +
                    "3\n" +
                    "2 -869545\n" +
                    "1 116455\n" +
                    "2 274648\n" +
                    "2 -494886\n" +
                    "2 105448\n" +
                    "3\n" +
                    "2 668512\n" +
                    "2 944530\n" +
                    "3\n" +
                    "1 887265\n" +
                    "2 864073\n" +
                    "1 902579\n" +
                    "2 16727\n" +
                    "3\n" +
                    "2 237993\n" +
                    "1 944015\n" +
                    "1 534412\n" +
                    "1 237406\n" +
                    "2 494532\n" +
                    "1 -680528\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 207363\n" +
                    "1 135934\n" +
                    "1 199391\n" +
                    "3\n" +
                    "3\n" +
                    "2 439517\n" +
                    "1 582355\n" +
                    "2 487962\n" +
                    "1 754042\n" +
                    "3\n" +
                    "1 60044\n" +
                    "1 326344\n" +
                    "2 -950756\n" +
                    "2 965731\n" +
                    "1 186018\n" +
                    "3\n" +
                    "3\n" +
                    "1 202513\n" +
                    "1 565691\n" +
                    "2 552095\n" +
                    "2 844265\n" +
                    "2 56270\n" +
                    "1 -94801\n" +
                    "1 845310\n" +
                    "3\n" +
                    "1 601664\n" +
                    "1 260835\n" +
                    "1 654991\n" +
                    "1 903406\n" +
                    "2 924463\n" +
                    "1 44138\n" +
                    "1 529085\n" +
                    "2 276239\n" +
                    "2 301653\n" +
                    "2 925993\n" +
                    "2 316937\n" +
                    "2 699753\n" +
                    "1 292729\n" +
                    "2 117368\n" +
                    "1 969918\n" +
                    "2 606109\n" +
                    "2 95953\n" +
                    "1 279330\n" +
                    "2 975122\n" +
                    "1 72171\n" +
                    "1 117130\n" +
                    "2 67458\n" +
                    "3\n" +
                    "2 831350\n" +
                    "2 222744\n" +
                    "1 241235\n" +
                    "2 264528\n" +
                    "1 751844\n" +
                    "2 836464\n" +
                    "1 553736\n" +
                    "3\n" +
                    "2 450773\n" +
                    "3\n" +
                    "1 14456\n" +
                    "2 618341\n" +
                    "2 -525578\n" +
                    "1 326492\n" +
                    "1 333834\n" +
                    "1 677485\n" +
                    "1 546272\n" +
                    "3\n" +
                    "2 769888\n" +
                    "3\n" +
                    "1 618855\n" +
                    "3\n" +
                    "2 287098\n" +
                    "2 -94801\n" +
                    "3\n" +
                    "2 279330\n" +
                    "1 834443\n" +
                    "1 637838\n" +
                    "3\n" +
                    "1 521607\n" +
                    "1 247520\n" +
                    "1 162516\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 247520\n" +
                    "1 823221\n" +
                    "3\n" +
                    "2 689696\n" +
                    "1 869901\n" +
                    "3\n" +
                    "2 806736\n" +
                    "1 53123\n" +
                    "1 93177\n" +
                    "1 -256428\n" +
                    "3\n" +
                    "1 656142\n" +
                    "1 182174\n" +
                    "1 313755\n" +
                    "1 906850\n" +
                    "1 27073\n" +
                    "1 467227\n" +
                    "1 71179\n" +
                    "3\n" +
                    "3\n" +
                    "1 713946\n" +
                    "1 129168\n" +
                    "1 248000\n" +
                    "1 913631\n" +
                    "2 199391\n" +
                    "1 997113\n" +
                    "1 567410\n" +
                    "2 614139\n" +
                    "1 890199\n" +
                    "2 630573\n" +
                    "1 282972\n" +
                    "1 942903\n" +
                    "2 220134\n" +
                    "1 146864\n" +
                    "1 968954\n" +
                    "1 402077\n" +
                    "2 71179\n" +
                    "2 553952\n" +
                    "1 -131198\n" +
                    "1 126912\n" +
                    "1 405219\n" +
                    "3\n" +
                    "1 947120\n" +
                    "1 55597\n" +
                    "1 447963\n" +
                    "1 751331\n" +
                    "3\n" +
                    "1 276804\n" +
                    "1 863917\n" +
                    "1 -359827\n" +
                    "1 501245\n" +
                    "1 443244\n" +
                    "3\n" +
                    "1 19004\n" +
                    "1 295694\n" +
                    "2 582355\n" +
                    "1 760048\n" +
                    "1 210548\n" +
                    "2 299477\n" +
                    "3\n" +
                    "1 29016\n" +
                    "2 863917\n" +
                    "1 974076\n" +
                    "1 515877\n" +
                    "1 640065\n" +
                    "1 141751\n" +
                    "3\n" +
                    "1 354720\n" +
                    "1 185530\n" +
                    "1 501956\n" +
                    "3\n" +
                    "2 314643\n" +
                    "3\n" +
                    "1 485362\n" +
                    "1 418681\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 117130\n" +
                    "3\n" +
                    "1 898702\n" +
                    "2 253449\n" +
                    "1 126597\n" +
                    "1 814281\n" +
                    "1 898868\n" +
                    "1 798764\n" +
                    "3\n" +
                    "2 182174\n" +
                    "1 852484\n" +
                    "2 326344\n" +
                    "2 515877\n" +
                    "1 715679\n" +
                    "1 384303\n" +
                    "1 812503\n" +
                    "1 -725785\n" +
                    "3\n" +
                    "2 434508\n" +
                    "1 214823\n" +
                    "1 681870\n" +
                    "2 944634\n" +
                    "1 812008\n" +
                    "1 361139\n" +
                    "2 681870\n" +
                    "2 383159\n" +
                    "3\n" +
                    "1 450125\n" +
                    "2 -256428\n" +
                    "1 309027\n" +
                    "1 392671\n" +
                    "2 906850\n" +
                    "1 73363\n" +
                    "1 891857\n" +
                    "2 823221\n" +
                    "1 216513\n" +
                    "3\n" +
                    "3\n" +
                    "2 751331\n" +
                    "1 445821\n" +
                    "1 903505\n" +
                    "2 898702\n" +
                    "1 890328\n" +
                    "1 714756\n" +
                    "1 806021\n" +
                    "3\n" +
                    "1 793885\n" +
                    "2 378244\n" +
                    "1 758715\n" +
                    "3\n" +
                    "1 874703\n" +
                    "1 142107\n" +
                    "3\n" +
                    "3\n" +
                    "1 324982\n" +
                    "2 -981246\n" +
                    "1 306685\n" +
                    "2 654991\n" +
                    "2 714393\n" +
                    "1 769021\n" +
                    "1 -196486\n" +
                    "2 812008\n" +
                    "1 523029\n" +
                    "1 591965\n" +
                    "2 418681\n" +
                    "1 681624\n" +
                    "3\n" +
                    "1 614795\n" +
                    "1 890360\n" +
                    "1 493470\n" +
                    "2 214823\n" +
                    "3\n" +
                    "2 135934\n" +
                    "1 78172\n" +
                    "1 194132\n" +
                    "2 651443\n" +
                    "1 -487344\n" +
                    "1 384064\n" +
                    "2 724986\n" +
                    "3\n" +
                    "3\n" +
                    "2 793885\n" +
                    "3\n" +
                    "1 946156\n" +
                    "1 265008\n" +
                    "3\n" +
                    "1 328423\n" +
                    "2 845310\n" +
                    "1 762882\n" +
                    "1 -90563\n" +
                    "3\n" +
                    "1 162874\n" +
                    "1 -752413\n" +
                    "1 -588704\n" +
                    "3\n" +
                    "2 534412\n" +
                    "3\n" +
                    "2 276804\n" +
                    "1 914751\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "1 455663\n" +
                    "1 800932\n" +
                    "1 225749\n" +
                    "1 132623\n" +
                    "2 292729\n" +
                    "1 819053\n" +
                    "2 467227\n" +
                    "2 282972\n" +
                    "3\n" +
                    "1 58750\n" +
                    "1 351851\n" +
                    "2 852484\n" +
                    "1 813487\n" +
                    "1 97511\n" +
                    "3\n" +
                    "2 942903\n" +
                    "2 677485\n" +
                    "2 754042\n" +
                    "3\n" +
                    "3\n" +
                    "1 -176330\n" +
                    "1 980707\n" +
                    "1 291988\n" +
                    "3\n" +
                    "3\n" +
                    "1 915484\n" +
                    "1 804716\n" +
                    "1 510322\n" +
                    "3\n" +
                    "1 260276\n" +
                    "2 210548\n" +
                    "1 74136\n" +
                    "2 445821\n" +
                    "1 563660\n" +
                    "1 91255\n" +
                    "2 891857\n" +
                    "1 837179\n" +
                    "3\n" +
                    "3\n" +
                    "3\n" +
                    "2 -680528\n" +
                    "2 605532\n" +
                    "1 763401\n" +
                    "3\n" +
                    "2 50592\n" +
                    "1 120120\n" +
                    "3\n" +
                    "2 14456\n" +
                    "1 467756\n" +
                    "2 -359827\n" +
                    "1 959578\n" +
                    "3\n" +
                    "1 61665\n" +
                    "1 716740\n" +
                    "3\n" +
                    "2 392671\n" +
                    "1 477298\n" +
                    "1 616845\n" +
                    "1 916625\n" +
                    "1 911254\n" +
                    "2 120120\n" +
                    "2 902579\n" +
                    "2 874703\n" +
                    "1 341913\n" +
                    "1 168312\n" +
                    "3\n" +
                    "1 354905\n" +
                    "1 532275\n" +
                    "3\n" +
                    "2 493470\n" +
                    "1 553595\n" +
                    "1 268360\n" +
                    "2 237130\n" +
                    "2 248000\n" +
                    "1 -546677\n" +
                    "3\n" +
                    "2 73363\n" +
                    "2 803700\n" +
                    "1 620041\n" +
                    "3\n" +
                    "1 93462\n" +
                    "1 369248\n" +
                    "1 312542\n" +
                    "3\n" +
                    "1 457284\n" +
                    "1 883209\n" +
                    "1 280299\n" +
                    "3\n" +
                    "1 539390\n" +
                    "1 351598\n" +
                    "3\n" +
                    "3\n" +
                    "1 911149\n" +
                    "1 602251\n" +
                    "3\n" +
                    "2 869901\n" +
                    "1 866195\n" +
                    "2 677289\n" +
                    "1 810571\n" +
                    "1 18499\n" +
                    "1 343499\n" +
                    "2 141751\n" +
                    "2 129168\n" +
                    "1 162129\n" +
                    "3\n" +
                    "1 2964\n" +
                    "1 405782\n" +
                    "1 166001\n" +
                    "3\n" +
                    "1 610860\n" +
                    "1 470249\n" +
                    "1 388164\n" +
                    "1 380359\n" +
                    "2 890199\n" +
                    "3\n" +
                    "1 249971\n" +
                    "3\n" +
                    "1 -886902\n" +
                    "2 -926919\n" +
                    "1 123919\n" +
                    "1 776403\n" +
                    "1 607790\n" +
                    "2 510322\n" +
                    "1 703957\n" +
                    "1 695821\n" +
                    "3\n" +
                    "1 758075\n" +
                    "1 600205\n" +
                    "3\n" +
                    "1 777163\n" +
                    "1 146902\n" +
                    "1 389807\n" +
                    "1 370105\n" +
                    "1 -493872\n" +
                    "3\n" +
                    "2 380359\n" +
                    "1 326410\n" +
                    "3\n" +
                    "3\n" +
                    "2 911254\n" +
                    "1 936707\n" +
                    "1 -416119\n" +
                    "2 -886902\n" +
                    "3\n" +
                    "3\n" +
                    "1 6934\n" +
                    "3\n" +
                    "3\n" +
                    "1 146020\n" +
                    "3\n" +
                    "1 115088\n" +
                    "3\n" +
                    "1 602488\n" +
                    "3\n" +
                    "1 454662\n" +
                    "3\n" +
                    "3\n" +
                    "2 107701\n" +
                    "2 969918\n" +
                    "1 491677\n" +
                    "2 760048\n" +
                    "1 619812\n" +
                    "2 19004\n" +
                    "1 475901\n" +
                    "3\n" +
                    "2 714756\n" +
                    "1 908156\n" +
                    "2 384064\n" +
                    "1 743758\n" +
                    "2 607790\n" +
                    "1 -496714\n" +
                    "3\n" +
                    "1 340214\n" +
                    "1 109981\n" +
                    "3\n" +
                    "3\n" +
                    "1 479642\n" +
                    "1 521347\n" +
                    "1 657035\n" +
                    "2 162874\n" +
                    "2 265008\n" +
                    "1 10204\n" +
                    "1 473121\n" +
                    "1 467501\n" +
                    "1 488385\n" +
                    "1 937658\n" +
                    "1 -616492\n" +
                    "3\n" +
                    "2 952116\n" +
                    "1 800576\n" +
                    "2 974076\n" +
                    "2 44138\n" +
                    "1 963014\n" +
                    "2 834443\n" +
                    "3\n" +
                    "1 77191\n" +
                    "1 288313\n" +
                    "1 36829\n" +
                    "1 41513\n" +
                    "2 351851\n" +
                    "1 78193\n" +
                    "1 120102\n" +
                    "2 616845\n" +
                    "3\n" +
                    "1 522862\n" +
                    "1 957309\n" +
                    "1 841850\n" +
                    "2 74136\n" +
                    "2 915484\n" +
                    "1 709024\n" +
                    "2 324982\n" +
                    "1 383817\n" +
                    "2 142107\n" +
                    "3\n" +
                    "1 62152\n" +
                    "2 618855\n" +
                    "3\n" +
                    "3";
}
