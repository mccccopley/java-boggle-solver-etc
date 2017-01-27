package boggle;

/**
 * Created by mccccopley on 1/26/2017.
 */
public interface IWordDictionary {
    void InsertWord(String word);
    IWordDictIter GetIterator();
}
