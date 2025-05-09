package app;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    Box box;
    Table table;
    Game() {
        players = new ArrayList<>();
        box = new Box();
        table = new Table();
    }
    void add_player(Player player) {
        players.add(player);
    }
    void remove_player(int nr) {
        players.remove(nr);
    }

    public static void main(String args[]){

    }
}
