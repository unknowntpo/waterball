package org.example;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ShowdownGame game = new ShowdownGame();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new AIPlayer(0, game));
        players.add(new AIPlayer(1, game));
        players.add(new HumanPlayer(2, game));
        players.add(new HumanPlayer(3, game));
        game.joinPlayers(players);
        game.start();
        Player winner = game.getWinner();
        winner.getName();
    }
}
