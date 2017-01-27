package boggle;

import java.util.List;

/**
 * Created by mccccopley on 1/26/2017.
 */
public interface IBoggleBoard {
    void Initialize(char[] letters, int columns);
    List<IBoggleTile> GetRootTiles();
}
