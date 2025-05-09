package app;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    Box box;
    Table table;
    Game() throws IOException {
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

    public static void main(String[] args) {
        Game game = null;
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert game != null;
        System.out.println(game.box.tiles.size());
    }
}
