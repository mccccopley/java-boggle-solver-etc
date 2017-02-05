package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackPlayer {
    private BlackJackHand hand;

    public BlackJackPlayer() {}

    public void startHand(Card hiddenCard) {
        this.hand = new BlackJackHand(hiddenCard);
    }

    public void addShowingCard(Card showingCard) {
        this.hand.addShowingCard(showingCard);
    }

    public void returnCards() {
        this.hand = null;
    }
}
