package org.example;

public class Card {
    private Rank rank;
    private Suit suit;

    public Rank getRank() {
        return rank;
    }


    public Suit getSuit() {
        return suit;
    }

    private boolean visible;
    private Player owner;

    public Player getOwner() {
        return owner;
    }


    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NIGHT, TEN, J, Q, K, A
    }

    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE
    }

    public boolean isVisible() {
        return visible;
    }


    public Card(Rank rank, Suit suit, boolean visible, Player owner) {
        this.rank = rank;
        this.suit = suit;
        this.visible = visible;
        this.owner = owner;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}


