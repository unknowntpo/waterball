package org.example;

import java.util.List;
import java.util.ArrayList;

import org.example.Card;

public abstract class Player {
    protected ShowdownGame game;
    protected List<Card> hand;
    protected boolean canExchange = true;

    protected int ID;

    public void setHand(List<Card> hand) {
        this.hand = hand;
        for (Card c : hand) {
            c.setOwner(this);
        }
    }

    public Player(int iD, ShowdownGame game) {
        ID = iD;
        hand = new ArrayList<Card>();
        this.game = game;
    }

    protected int point;

    private String name;

    // return the index in hand that this player wanna pick
    public abstract int getShowCardInput();

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        card.setOwner(this);
        hand.add(card);
    }

    public void nameHimself() {
        String[] names = { "Alice", "Bob", "Kelly", "Kelvin" };
        this.name = names[(int) (Math.random() * names.length)];
    }

    public abstract Player pickPlayerExcept(int ID);

    public abstract void exchangeHands(int round);

    public Card showCard() {
        int idx = getShowCardInput();
        Card card = hand.get(idx);
        hand.remove(idx);
        return card;
    }

    public String getName() {
        return name;
    }

    public abstract boolean useExchangeHands();

    public void addPointBy(int i) {
        point += i;
    }

    public ShowdownGame getGame() {
        return game;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getPoint() {
        return point;
    }

    public void setGame(ShowdownGame game) {
        this.game = game;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setName(String name) {
        this.name = name;
    }
}
