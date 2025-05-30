package app.viewmodels;

import app.model.Game;
import app.model.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBarViewModel {
    private final Map<Player, PlayerViewModel> players = new HashMap<>();

    public SideBarViewModel(Game game) {
        for (Player player : game.getPlayers()) {
            players.put(player, new PlayerViewModel(player));
        }

        players.get(game.getCurrentPlayerProperty().get()).setSelected(true);
        game.getCurrentPlayerProperty().addListener((_, oldPlayer, newPlayer) -> {
            players.get(oldPlayer).setSelected(false);
            players.get(newPlayer).setSelected(true);
        });
    }

    public List<PlayerViewModel> getPlayersViewModels() {
        return new ArrayList<>(players.values());
    }
}