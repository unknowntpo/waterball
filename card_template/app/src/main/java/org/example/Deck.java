package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private List<Card> cards;

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Deck() {
        cards = new ArrayList<Card>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit, false, null));
            }
        }
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
