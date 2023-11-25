package org.example;

import java.util.Scanner;
import java.io.Console;

public class HumanPlayer extends Player {

    public HumanPlayer(int iD, ShowdownGame game) {
        super(iD, game);
    }

    @Override
    public int getInput() {
        System.out.printf("pick 1 card idx in range [0, %d]\n", 0, super.getHand().size() - 1);
        Console console = System.console();
        String input = "";
        if (console != null) {
            input = console.readLine();
        }
        int idx = Integer.parseInt(input);
        return idx;
    }

    @Override
    public boolean useExchangeHands() {
        System.out.println("use exchangeHands is this turn ? (y/n)");
        Console console = System.console();
        String input = "";
        if (console != null) {
            input = console.readLine();
        }
        boolean use;
        switch (input) {
            case "y":
            case "Y":
                use = true;
                break;
            case "n":
            case "N":
                use = false;
                break;
            default:
                throw new IllegalArgumentException(String.format("want y/n, got %v"));
        }
        return use;
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
        super.canExchange = false;
        game.setExchangeHandsByRound(round, ex);
    }

    @Override
    public Player pickPlayerExcept(int ID) {
        int numPlayers = super.game.getPlayers().size();

        System.out.printf("choose id betwen [0, %d] except %d to exchange cards\n", this.ID, numPlayers - 1);
        String input = "";
        Console console = System.console();
        if (console != null) {
            input = console.readLine();
        }
        // Scanner scanner = new Scanner(System.in);
        // String input = scanner.nextLine();
        // scanner.close();

        int id = Integer.parseInt(input);
        id = super.game.validatePlayerID(id, this.ID);
        return game.getPlayers().get(id);
    }
}
