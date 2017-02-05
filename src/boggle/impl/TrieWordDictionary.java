package boggle.impl;

import boggle.IWordDictIter;
import boggle.IWordDictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by mccccopley on 1/26/2017.
 */

class TrieNode {
    private static final int ChildCount = 26;
    private static final char FirstChildLetter = 'A';

    public char wordLetter;
    public boolean isRoot;
    public boolean isCompleteWord;
    public TrieNode[] children;

    TrieNode(char wordLetter, boolean isCompleteWord, boolean isRoot) {
        this.wordLetter = wordLetter;
        this.isRoot = isRoot;
        this.isCompleteWord = isCompleteWord;
        this.children = new TrieNode[ChildCount];
    }

    static int GetLetterChildIndex(char letter) {
        return letter - FirstChildLetter;
    }

    TrieNode GetFirstChild() {
        int letterIndex = 0;

        while (letterIndex < ChildCount) {
            if (this.children[letterIndex] != null) {
                return this.children[letterIndex];
            }

            letterIndex++;
        }

        return null;
    }

    TrieNode GetNextSibling(TrieNode child) {
        int nextLetterIndex = GetLetterChildIndex(child.wordLetter) + 1;

        while (nextLetterIndex < ChildCount) {
            if (this.children[nextLetterIndex] != null) {
                return this.children[nextLetterIndex];
            }

            nextLetterIndex++;
        }

        return null;
    }

    TrieNode GetOrInsertChildForLetter(char letter, boolean isEndOfWord) {
        int childOffset = GetLetterChildIndex(letter);

        if (this.children[childOffset] == null) {
            this.children[childOffset] = new TrieNode(letter, isEndOfWord, false);
        }

        return this.children[childOffset];
    }
}

class TrieWordDictIter implements IWordDictIter {
    String cachedNextWord;
    StringBuilder internalStringBuilder;
    LinkedList<TrieNode> workingWordChars;

    TrieWordDictIter(TrieNode rootNode) {
        this.cachedNextWord = null;
        this.internalStringBuilder = new StringBuilder();
        this.workingWordChars = new LinkedList<>();

        this.workingWordChars.add(rootNode);
    }

    void CacheWorkingWord() {
        this.internalStringBuilder.setLength(0);
        Iterator<TrieNode> nodeIter = this.workingWordChars.iterator();
        while (nodeIter.hasNext()) {
            TrieNode nextNode = nodeIter.next();

            if (!nextNode.isRoot) {
                this.internalStringBuilder.append(nextNode.wordLetter);
            }
        }
        this.cachedNextWord = this.internalStringBuilder.toString();
    }

    void TraverseUntilWordFound() {
        while (!this.workingWordChars.isEmpty()) {
            // get last node from working set
            TrieNode lastWorkingSetNode = this.workingWordChars.getLast();

            // push the first child onto the stack
            TrieNode firstChild = lastWorkingSetNode.GetFirstChild();

            // if there are no children, then remove from the working set
            if (firstChild == null) {
                this.workingWordChars.removeLast();

                // pursue the next sibling for the parent if possible
                while (!this.workingWordChars.isEmpty()) {
                    TrieNode parentNode = this.workingWordChars.getLast();
                    TrieNode nextSibling = parentNode.GetNextSibling(lastWorkingSetNode);

                    // keep marching back upward until we can find another sibling node
                    if (nextSibling == null) {
                        lastWorkingSetNode = parentNode;
                        this.workingWordChars.removeLast();
                    }

                    else {
                        this.workingWordChars.add(nextSibling);

                        // if we have found a complete word, then we can stop traversing
                        if (nextSibling.isCompleteWord) {
                            CacheWorkingWord();
                            return;
                        }

                        break;
                    }
                }
            }

            // otherwise add the child to the working set
            else {
                this.workingWordChars.add(firstChild);

                // if we have found a complete word, then we can stop traversing
                if (firstChild.isCompleteWord) {
                    CacheWorkingWord();
                    return;
                }
            }
        }
    }

    @Override
    public boolean HasNext() {
        if (this.cachedNextWord != null) {
            return true;
        }

        if (this.cachedNextWord == null && this.workingWordChars.isEmpty()) {
            return false;
        }

        TraverseUntilWordFound();
        return this.cachedNextWord != null;
    }

    @Override
    public String GetNext() {
        if (this.cachedNextWord != null) {
            String nextWord = this.cachedNextWord;
            this.cachedNextWord = null;
            return nextWord;
        }

        if (this.cachedNextWord == null && this.workingWordChars.isEmpty()) {
            return null;
        }

        TraverseUntilWordFound();
        String nextWord = this.cachedNextWord;
        this.cachedNextWord = null;
        return nextWord;
    }
}

public class TrieWordDictionary implements IWordDictionary {
    TrieNode rootNode;

    public TrieWordDictionary() {
        this.rootNode = new TrieNode('*', false, true);
    }

    @Override
    public void InsertWord(String word) {
        String uppercaseWord = word.toUpperCase();
        char[] wordLetters = uppercaseWord.toCharArray();
        int letterCount = wordLetters.length;
        int lastLetterIndex = letterCount - 1;

        TrieNode workingNode = this.rootNode;
        int letterIndex = 0;
        while (letterIndex < letterCount) {
            char currentLetter = wordLetters[letterIndex];
            workingNode = workingNode.GetOrInsertChildForLetter(currentLetter, letterIndex == lastLetterIndex);
            letterIndex++;
        }
    }

    @Override
    public IWordDictIter GetIterator() {
        return new TrieWordDictIter(this.rootNode);
    }
}
