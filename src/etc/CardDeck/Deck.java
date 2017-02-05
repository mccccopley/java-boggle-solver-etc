package etc.CardDeck;

import java.util.Random;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class Deck {
    private static Random randomNumberGenerator = new Random();
    private Card[] cards;
    private int topIndex = 0;

    public Deck(ICardFactory cardFactory) { this.cards = cardFactory.generateCards(); }

    public void shuffleDeck() {
        int lastIndex = cards.length - 1;
        while (lastIndex > 0) {
            int randomCardIndex = randomNumberGenerator.nextInt() % (lastIndex + 1);
            if (randomCardIndex != lastIndex) {
                Card bottomCard = this.cards[lastIndex];
                this.cards[lastIndex] = this.cards[randomCardIndex];
                this.cards[randomCardIndex] = bottomCard;
            }
            --lastIndex;
        }
    }

    public Card dealCard() {
        if (this.topIndex == cards.length) {
            return null;
        }

        Card topCard = this.cards[this.topIndex];
        topCard.setIsInDeck(false);
        ++this.topIndex;
        return topCard;
    }

    public void returnAllCards() {
        while (this.topIndex > 0) {
            --this.topIndex;
            this.cards[this.topIndex].setIsInDeck(true);
        }
        this.topIndex = 0;
    }
}
