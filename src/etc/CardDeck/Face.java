package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public enum Face {
    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13);

    private final int value;

    Face(int value) { this.value = value; }
    public int getValue() { return this.value; }
    public boolean getIsJQorK() { return this.equals(Jack) || this.equals(Queen) || this.equals(King); }
    public static Face getFromValue(int value) {
        Face[] values = Face.values();
        for (Face face : values) {
            if( face.value == value) {
                return face;
            }
        }
        return null;
    }
}
