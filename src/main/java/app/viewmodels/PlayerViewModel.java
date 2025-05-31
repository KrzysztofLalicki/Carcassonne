package app.viewmodels;


import app.model.Player;
import javafx.beans.property.*;

public class PlayerViewModel {
    private final Player player;

    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public PlayerViewModel(Player player) {
        this.player = player;
    }

    public StringProperty getNameProperty() { return player.getNameProperty(); }
    public IntegerProperty getScoreProperty() {return player.getPointsProperty();}
    public BooleanProperty getSelectedProperty() { return selected; }

    public void setSelected(boolean value) { selected.set(value); }
}