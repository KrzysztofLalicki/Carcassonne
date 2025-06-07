package app.viewmodels;

import app.model.Game;
import app.model.GameStateChangeListener;
import app.model.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBarViewModel implements GameStateChangeListener {
    private final Game game;
    private final Map<Player, PlayerViewModel> players = new HashMap<>();
    private Player selectedPlayer;

    public List<PlayerViewModel> getPlayersViewModels() {return new ArrayList<>(players.values());}

    public SideBarViewModel(Game game) {
        this.game = game;
        game.addGameStateChangeListener(this);

        for (Player player : game.getPlayers())
            players.put(player, new PlayerViewModel(player));

        updateSelectedPlayer();
    }

    private void updateSelectedPlayer() {
        if(selectedPlayer != null)
            players.get(selectedPlayer).setSelected(false);
        players.get(game.getCurrentPlayer()).setSelected(true);
        selectedPlayer = game.getCurrentPlayer();
    }

    @Override
    public void onCurrentPlayerChange() {
        updateSelectedPlayer();
    }
}