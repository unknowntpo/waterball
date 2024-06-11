package org.example;

public class AIPlayer extends Player {
    public AIPlayer(int iD, ShowdownGame game) {
        super(iD, game);
    }

    @Override
    public int getShowCardInput() {
        // TODO Auto-generated method stub
        return (int) Math.random() * super.hand.size();
    }

    @Override
    public boolean useExchangeHands() {
        return Math.round(Math.random()) == 1 ? true : false;
    }

    @Override
    public void exchangeHands(int round) {
        if (!canExchange) {
            // FIXME: Which exception to throw ?
            throw new IllegalArgumentException("you can't exchange anymore");
        }

        Player player = this.pickPlayerExcept(this.getID());
        ExchangeHands ex = new ExchangeHands(this, player);
        ex.exchange();
        game.setExchangeHandsByRound(round, ex);
    }

    @Override
    public Player pickPlayerExcept(int ID) {
        int numPlayers = super.game.getPlayers().size();
        int id = ID;
        while (id == ID) {
            id = (int) (Math.random() * numPlayers);
        }
        return game.getPlayers().get(id);
    }
}
