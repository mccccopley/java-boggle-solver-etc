package boggle;

/**
 * Created by mccccopley on 1/26/2017.
 */
public interface IBoggleTile {
    char GetLetter();
    IBoggleTile GetFirstUnvisitedNeighborWithLetter(char letter);
    IBoggleTile GetNextUnvisitedNeighborWithLetter(IBoggleTile neighbor, char letter);
    void MarkVisited(boolean isVisited);
    boolean IsVisited();
}
