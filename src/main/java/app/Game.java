package app;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    Box box;
    Table table;
    Game() throws IOException {
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
}
