package etc.CardDeck;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackDealer {
    private static ICardFactory cardFactory = new BlackJackCardFactory();

    private BlackJackTable table;
    private Deck cardDeck;

    public BlackJackDealer(BlackJackTable table) {
        this.table = table;
        this.cardDeck = new Deck(cardFactory);
    }

    public void beginDeal() {
        this.cardDeck.shuffleDeck();
    }

    public void endDeal() {
        this.cardDeck.returnAllCards();
    }

    public void DealHiddenCardToPlayer(BlackJackPlayer player) {
        Card dealtCard = this.cardDeck.dealCard();
        player.startHand(dealtCard);
    }

    public void DealShowingCardToPlayer(BlackJackPlayer player) {
        Card dealtCard = this.cardDeck.dealCard();
        player.addShowingCard(dealtCard);
    }
}
