package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public abstract class Card {
    protected Suit suit;
    protected Face face;
    private boolean isInDeck = true;

    protected Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    public boolean getIsInDeck() { return this.isInDeck; }
    public void setIsInDeck(boolean isInDeck) { this.isInDeck = isInDeck; }

    public abstract int getMinScore();
    public abstract int getMaxScore();
}
