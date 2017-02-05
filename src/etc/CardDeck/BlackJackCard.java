package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackCard extends Card {
    public BlackJackCard(Suit suit, Face face) {
        super(suit, face);
    }

    @Override
    public int getMinScore() {
        if (this.face == Face.Ace) {
            return 1;
        } else if (this.face.getIsJQorK()) {
            return 10;
        } else {
            return this.face.getValue();
        }
    }

    @Override
    public int getMaxScore() {
        if (this.face == Face.Ace) {
            return 10;
        } else {
            return this.getMinScore();
        }
    }
}
