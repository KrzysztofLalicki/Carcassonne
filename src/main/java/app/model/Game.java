package app.model;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    Box box;
    Table table;
    public Game(){
        players = new ArrayList<>();
        table = new Table();
        box = new Box();
    }
    void add_player(Player player) {
        players.add(player);
    }
    void remove_player(int nr) {
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
