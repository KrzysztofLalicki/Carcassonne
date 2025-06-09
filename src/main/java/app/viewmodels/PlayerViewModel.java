package app.viewmodels;


import app.model.Player;
import app.model.PlayerChangeListener;
import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class PlayerViewModel implements PlayerChangeListener {
    private final Player player;

    private final String name;
    private final IntegerProperty score = new SimpleIntegerProperty();
    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final IntegerProperty followers = new SimpleIntegerProperty();
    private final Color color;

    public String getName() { return name; }
    public IntegerProperty getScoreProperty() { return score; }
    public BooleanProperty getSelectedProperty() { return selected; }
    public void setSelected(boolean value) { selected.set(value); }
    public IntegerProperty getFollowersProperty() {return followers;}
    public Color getColor() { return color; }

    public PlayerViewModel(Player player) {
        player.addPlayerChangeListener(this);
        this.player = player;
        this.name = player.getName();
        this.color = player.getColor();

        score.set(player.getPoints());
        followers.set(player.getFollowersNumber());
    }

    @Override
    public void onScoreChange() {
        score.set(player.getPoints());
    }

    @Override
    public void onFollowersNumberChange() {
        followers.set(player.getFollowersNumber());
    }
}