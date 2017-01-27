package boggle.impl;

import boggle.IBoggleBoard;
import boggle.IBoggleTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mccccopley on 1/26/2017.
 */

class BoggleBoardTile implements IBoggleTile {
    public char letter;
    public boolean isVisited;
    public BoggleBoardTile[] neighboringTiles;

    public BoggleBoardTile(char letter) {
        this.letter = Character.toUpperCase(letter);
        this.isVisited = false;
    }

    public char GetLetter() {
        return letter;
    }

    public void SetNeighbors(ArrayList<BoggleBoardTile> neighbors) {
        if (neighbors.size() > 0) {
            int numberOfNeighbors = neighbors.size();
            neighboringTiles = new BoggleBoardTile[numberOfNeighbors];

            for (int neighborTileIndex = 0; neighborTileIndex < numberOfNeighbors; ++neighborTileIndex) {
                this.neighboringTiles[neighborTileIndex] = neighbors.get(neighborTileIndex);
            }
        }
    }

    private IBoggleTile GetFirstUnvisitedNeighborAfterIndex(char letter, int neighborIndex, int stopBeforeIndex) {
        while (neighborIndex < stopBeforeIndex) {
            BoggleBoardTile neighborTile = this.neighboringTiles[neighborIndex];
            if (neighborTile != null && !neighborTile.IsVisited() && neighborTile.letter == letter) {
                return neighborTile;
            }

            neighborIndex++;
        }

        return null;
    }

    public IBoggleTile GetFirstUnvisitedNeighborWithLetter(char letter) {
        int neighborCount = this.neighboringTiles.length;
        int neighborIndex = 0;

        return GetFirstUnvisitedNeighborAfterIndex(letter, neighborIndex, neighborCount);
    }

    public IBoggleTile GetNextUnvisitedNeighborWithLetter(IBoggleTile neighbor, char letter) {
        int neighborCount = this.neighboringTiles.length;
        int neighborIndex = 0;

        while (neighborIndex < neighborCount) {
            BoggleBoardTile neighborTile = this.neighboringTiles[neighborIndex];
            neighborIndex++;
            if (neighborTile == neighbor) {
                break;
            }
        }

        return GetFirstUnvisitedNeighborAfterIndex(letter, neighborIndex, neighborCount);
    }

    public void MarkVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean IsVisited() {
        return this.isVisited;
    }
}

public class BoggleBoard implements IBoggleBoard {
    BoggleBoardTile[] tiles;

    public void Initialize(char[] letters, int columns) {
        ArrayList<BoggleBoardTile> neighborList = new ArrayList<>();

        // first create tiles
        int numberOfTiles = letters.length;
        tiles = new BoggleBoardTile[numberOfTiles];
        for (int tileIndex = 0; tileIndex < numberOfTiles; tileIndex++ ) {
            tiles[tileIndex] = new BoggleBoardTile(letters[tileIndex]);
        }

        // then assign neighbors
        int rows = numberOfTiles / columns;
        int lastRow = rows - 1;
        int lastColumn = columns - 1;
        int tileIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                BoggleBoardTile tile = this.tiles[tileIndex];
                neighborList.clear();

                if (row > 0) {
                    if (column > 0) {
                        neighborList.add(this.tiles[tileIndex - rows - 1]);
                    }

                    if (column < lastColumn) {
                        neighborList.add(this.tiles[tileIndex - rows + 1]);
                    }

                    neighborList.add(this.tiles[tileIndex - rows]);
                }

                if (row < lastRow) {
                    if (column > 0) {
                        neighborList.add(this.tiles[tileIndex + rows - 1]);
                    }

                    if (column < lastColumn) {
                        neighborList.add(this.tiles[tileIndex + rows + 1]);
                    }

                    neighborList.add(this.tiles[tileIndex + rows]);
                }

                if (column > 0) {
                    neighborList.add(this.tiles[tileIndex - 1]);
                }

                if (column < lastColumn) {
                    neighborList.add(this.tiles[tileIndex + 1]);
                }

                tile.SetNeighbors(neighborList);

                tileIndex++;
            }
        }
    }

    public List<IBoggleTile> GetRootTiles() {
        return Arrays.asList(this.tiles);
    }
}
