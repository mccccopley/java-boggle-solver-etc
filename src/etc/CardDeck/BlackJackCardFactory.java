package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackCardFactory implements ICardFactory {
    public Card[] generateCards() {
        Suit[] suits = Suit.values();
        Face[] faces = Face.values();

        Card[] cards = new BlackJackCard[suits.length * faces.length];

        int cardIndex = 0;

        for (Suit suit : suits) {
            for (Face face : faces) {
                cards[cardIndex] = new BlackJackCard(suit, face);
                ++cardIndex;
            }
        }

        return cards;
    }
}
