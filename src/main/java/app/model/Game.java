package app.model;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players;
    private final Box box;
    private final Table table;
    public Game(){
        players = new ArrayList<>();
        table = new Table();
        box = new Box();
    }
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(int nr) {
        players.remove(nr);
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
}
