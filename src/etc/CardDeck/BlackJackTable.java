package etc.CardDeck;

import java.util.ArrayList;

/**
 * Created by mccccopley on 2/4/2017.
 */
public class BlackJackTable {
    private static final int MaxPlayers = 6;

    private BlackJackDealer dealer;
    private ArrayList<BlackJackPlayer> players;

    public BlackJackTable() {
        this.dealer = new BlackJackDealer(this);
    }

    public BlackJackPlayer AddPlayer() {
        if (players.size() == MaxPlayers) {
            return null;
        }

        BlackJackPlayer player = new BlackJackPlayer();
        this.players.add(player);
        return player;
    }

    public void RemovePlayer(BlackJackPlayer player) {
        players.remove(player);
    }

    public void DealRound() {
        this.dealer.beginDeal();
        for (BlackJackPlayer player : players) {
            dealer.DealHiddenCardToPlayer(player);
            dealer.DealShowingCardToPlayer(player);
        }
    }

    public void EndRound() {
        for (BlackJackPlayer player : players) {
            player.returnCards();
        }
        this.dealer.endDeal();
    }
}
