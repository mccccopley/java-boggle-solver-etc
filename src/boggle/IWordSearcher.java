package boggle;

import java.util.List;

/**
 * Created by mccccopley on 1/26/2017.
 */
public interface IWordSearcher {
    void FindWords(IWordDictionary wordDictionary, IBoggleBoard boggleBoard, List<String> foundWords);
    boolean IsWordPresent(String word, IBoggleBoard boggleBoard);
}
