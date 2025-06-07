package app.viewmodels;

import app.model.Game;
import app.model.GameStateChangeListener;
import app.model.Player;
import app.model.Tile;
import app.view.BoardSelector;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBarViewModel implements GameStateChangeListener {
    private final Game game;
    private final Map<Player, PlayerViewModel> players = new HashMap<>();
    private Player selectedPlayer;

    private BooleanProperty isActive;
    private ObjectProperty<Tile> previewTile;
    private ObjectProperty<BoardSelector.Outline> outlineProperty;

    public BooleanProperty getIsActive() {return isActive;}
    public ObjectProperty<Tile> getOnViewTile() {return previewTile;}
    public ObjectProperty<BoardSelector.Outline> getOutlineProperty() {return outlineProperty;}


    public List<PlayerViewModel> getPlayersViewModels() {return new ArrayList<>(players.values());}

    public SideBarViewModel(Game game, BoardSelectorViewModel boardSelectorViewModel) {
        this.game = game;
        game.addGameStateChangeListener(this);

        isActive = boardSelectorViewModel.getIsActive();
        previewTile = boardSelectorViewModel.getTile();
        outlineProperty = boardSelectorViewModel.getOutlineProperty();

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