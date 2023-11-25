package org.example;

import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ShowdownGame {
    private Deck deck;
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    private List<List<ExchangeHands>> changeBackEvents;

    public void setPlayers(List<Player> players) {
        this.players = Objects.requireNonNull(players);
    }

    public ShowdownGame() {
        this.deck = new Deck();
        this.changeBackEvents = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            this.changeBackEvents.add(new ArrayList<ExchangeHands>());
        }
    }

    public void joinPlayers(List<Player> players) {
        if (players.size() != 4)
            throw new IllegalArgumentException(String.format("Should join exactly 4 players, got %d", players.size()));
        setPlayers(players);
    }

    public void start() {
        // (ai) Ask p1 ~ p4 to name themselves
        // (aii) Shuffle the deck.
        for (Player p : players) {
            p.nameHimself();
        }
        deck.shuffle();

        int rounds = 13;
        // DrawCard phase:
        // P1 ~ p4 DrawCard until everyone has 13 cards.
        for (int i = 1; i <= rounds; i++) {
            for (int j = 0; j < players.size() && !deck.isEmpty(); j++) {
                players.get(j).drawCard(deck);
            }
        }

        // (c) 13 Rounds:
        // Take a turn
        // // Use exchangeHands or not
        //

        for (int i = 1; i <= rounds; i++) {
            // Take a turn
            // map from index of player to cards
            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < players.size(); j++) {
                Player player = players.get(i);
                if (player.useExchangeHands()) {
                    player.exchangeHands(i);
                }
                // FIXME: if this player has no card, then skip this turn.
                cards.add(player.showCard());
            }
            Player winner = getTurnWinner(cards);
            winner.addPointBy(1);
        }
    }

    // getTurnWinner gets the winner of this turn by the cards they shown.
    public Player getTurnWinner(List<Card> cards) {
        cards.sort(new CardComparator().reversed());
        return cards.get(0).getOwner();
    }

    public Player getWinner() {
        players.sort(Comparator.comparingInt(Player::getPoint).reversed());
        return players.get(0);
    }

    public void setExchangeHandsByRound(int round, ExchangeHands ex) {
        if (round > 13 || round < 1) {
            throw new IllegalArgumentException(String.format("round should be within 1 and 13, got %d", round));
        }
        changeBackEvents.get(round).add(ex);
    }

    public int validatePlayerID(int id, int except) {
        if (id < 0 || id >= players.size()) {
            throw new IllegalArgumentException(String.format("invalid player id"));
        }
        if (id == except) {
            throw new IllegalArgumentException(String.format("invalid player id"));
        }
        return 0;
    }
}
