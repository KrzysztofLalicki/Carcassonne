package app.viewmodels;


import app.model.Player;
import app.model.PlayerChangeListener;
import javafx.beans.property.*;

public class PlayerViewModel implements PlayerChangeListener {
    private final Player player;

    private final String name;
    private final IntegerProperty score = new SimpleIntegerProperty();
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public String getName() { return name; }
    public IntegerProperty getScoreProperty() { return score; }
    public BooleanProperty getSelectedProperty() { return selected; }
    public void setSelected(boolean value) { selected.set(value); }

    public PlayerViewModel(Player player) {
        player.addPlayerChangeListener(this);
        this.player = player;
        this.name = player.getName();

        score.set(player.getPoints());
    }

    @Override
    public void onScoreChange() {
        score.set(player.getPoints());
    }

    @Override
    public void onFollowersNumberChange() {

    }
}