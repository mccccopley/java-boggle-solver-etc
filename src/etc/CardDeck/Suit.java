package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public enum Suit {
    Club(0),
    Diamond(1),
    Heart(2),
    Spade(3);

    private final int value;

    Suit(int value) { this.value = value; }
    public int getValue() { return this.value; }
    public static Suit getFromValue(int value) {
        Suit[] values = Suit.values();
        for (Suit suit : values) {
            if( suit.value == value) {
                return suit;
            }
        }
        return null;
    }
}
