package boggle.impl;

import boggle.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mccccopley on 1/26/2017.
 */
public class BoggleWordFinder implements IWordSearcher {
    LinkedList<IBoggleTile> activeWordSearch;

    public BoggleWordFinder() {
        this.activeWordSearch = new LinkedList<>();
    }

    private void ResetActiveVisitedFlags() {
        this.activeWordSearch.forEach((IBoggleTile tile) -> tile.MarkVisited(false));
    }

    private boolean CanFindWordFromTile(String word, IBoggleTile tile) {
        if (tile.GetLetter() != word.charAt(0)) {
            return false;
        }

        activeWordSearch.clear();

        tile.MarkVisited(true);
        activeWordSearch.add(tile);

        while (!activeWordSearch.isEmpty()) {
            if (activeWordSearch.size() == word.length()) {
                ResetActiveVisitedFlags();
                return true;
            }

            IBoggleTile lastActiveTile = this.activeWordSearch.getLast();

            int nextLetterIndex = activeWordSearch.size();
            char nextLetter = word.charAt(nextLetterIndex);

            IBoggleTile firstChild = lastActiveTile.GetFirstUnvisitedNeighborWithLetter(nextLetter);
            if (firstChild == null) {
                lastActiveTile.MarkVisited(false);
                this.activeWordSearch.removeLast();

                // pursue the next sibling for the parent if possible
                while (!this.activeWordSearch.isEmpty()) {
                    IBoggleTile parentTile = this.activeWordSearch.getLast();
                    IBoggleTile nextNeighborTile = parentTile.GetNextUnvisitedNeighborWithLetter(lastActiveTile, lastActiveTile.GetLetter());

                    // keep marching back upward until we can find another sibling node
                    if (nextNeighborTile == null) {
                        lastActiveTile = parentTile;
                        lastActiveTile.MarkVisited(false);
                        this.activeWordSearch.removeLast();
                    }

                    else {
                        nextNeighborTile.MarkVisited(true);
                        this.activeWordSearch.add(nextNeighborTile);
                        break;
                    }
                }
            } else {
                firstChild.MarkVisited(true);
                this.activeWordSearch.add(firstChild);
            }
        }

        return false;
    }

    public void FindWords(IWordDictionary wordDictionary, IBoggleBoard boggleBoard, List<String> foundWords) {
        System.out.println("Searching for all words in the dictionary...");

        foundWords.clear();

        IWordDictIter dictIter = wordDictionary.GetIterator();
        while (dictIter.HasNext()) {
            String word = dictIter.GetNext();
            System.out.format("\n...Searching for word: [%s].", word);

            if (this.IsWordPresent(word, boggleBoard)) {
                foundWords.add(word);
            }
        }

        System.out.println();
    }

    public boolean IsWordPresent(String word, IBoggleBoard boggleBoard) {
        List<IBoggleTile> rootTiles = boggleBoard.GetRootTiles();

        boolean success;
        for (IBoggleTile tile : rootTiles) {
            System.out.format("tile {%c}.", tile.GetLetter());
            success = CanFindWordFromTile(word, tile);
            if (success) {
                return true;
            }
        }

        return false;
    }
}
