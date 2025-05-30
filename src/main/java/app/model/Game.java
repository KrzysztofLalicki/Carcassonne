package app.model;

import java.util.ArrayList;

public class Game {

    private final ArrayList<Player> players;
    private final Box box;
    private final Table table;
    private Integer currentPlayer;

    public Game(){
        players = new ArrayList<>();
        table = new Table(this);
        box = new Box();
        // currentPlayer = null;
        currentPlayer = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(int nr) {
        players.remove(nr);
    }
    public void nextPlayer() {
        box.giveTile();
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public void start() {
        currentPlayer = 0;
        box.giveTile();

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Table getTable() {
        return table;
    }
    public Box getBox() {
        return box;
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
