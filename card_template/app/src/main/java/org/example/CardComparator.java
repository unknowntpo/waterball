package org.example;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        // First compare by rank
        int suitComparison = c1.getSuit().compareTo(c2.getSuit());
        if (suitComparison != 0) {
            // can be c1.suit < c2.suit or c1.suit > c2.suit
            return suitComparison;
        }

        // c1 and c2 has same suit, compare rank
        int rankComparison = c1.getRank().compareTo(c2.getRank());
        if ( rankComparison == 0 ) throw new IllegalArgumentException("two cards can't be same Suit and Rank");

        return rankComparison;
    }
}
