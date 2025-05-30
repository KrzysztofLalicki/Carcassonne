package app.viewmodels;

import app.model.Game;
import app.model.Player;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

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
    }

    public List<PlayerViewModel> getPlayersViewModels() {
        return new ArrayList<>(players.values());
    }
}