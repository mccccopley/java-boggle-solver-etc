package etc.CardDeck;

import java.util.ArrayList;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackHand {
    private static final int BestScore = 21;

    private Card hiddenCard;
    private ArrayList<Card> showingCards;

    public BlackJackHand(Card hiddenCard) {
        this.hiddenCard = hiddenCard;
    }

    public void addShowingCard(Card showingCard) {
        if (this.showingCards == null) {
            this.showingCards = new ArrayList<>();
        }
        this.showingCards.add(showingCard);
    }

    public boolean getIsBusted() {
        return this.getBestScore() > BestScore;
    }

    public boolean hasBlackJack() {
        return this.getBestScore() == BestScore && this.showingCards.size() == 1;
    }

    public int getBestScore() {
        int minimumScore = this.hiddenCard.getMinScore();
        int maximumScore = this.hiddenCard.getMaxScore();

        for (Card showingCard : this.showingCards) {
            minimumScore += showingCard.getMinScore();
            maximumScore += showingCard.getMaxScore();
        }

        return maximumScore <= BestScore ? maximumScore : minimumScore;
    }
}
