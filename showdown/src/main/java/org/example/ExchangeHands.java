package org.example;

import java.util.List;
import java.util.Objects;

public class ExchangeHands {
    private Player leftPlayer;
    private Player rightPlayer;

    public ExchangeHands(Player leftPlayer, Player rightPlayer) {
        this.leftPlayer = Objects.requireNonNull(leftPlayer);
        this.rightPlayer = Objects.requireNonNull(rightPlayer);
        swap();
    }

    private void swap() {
        List<Card> leftCards = this.leftPlayer.getHand();
        List<Card> rightCards = this.rightPlayer.getHand();
        this.leftPlayer.setHand(rightCards);
        this.rightPlayer.setHand(leftCards);
    }

    public void exchange() {
        swap();
    }
}
