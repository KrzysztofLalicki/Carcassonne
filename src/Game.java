import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    Box box;
    Game() {
        players = new ArrayList<>();
        box = new Box();
    }
    void add_player(Player player) {
        players.add(player);
    }
    void remove_player(int nr) {
        players.remove(nr);
    }
}
