package app.viewmodels;

import app.model.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TileViewModel {
    Tile tile;
    public IntegerProperty rotation;

    public TileViewModel(Tile tile) {
        this.tile = tile;
        rotation = tile.getRotationPropert();
    }

}
