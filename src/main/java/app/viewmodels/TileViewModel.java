package app.viewmodels;

import app.model.Tile;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class TileViewModel {
    Tile tile;
    public IntegerProperty rotation = new SimpleIntegerProperty();
    public ObjectProperty<Image> image = new SimpleObjectProperty<>();

    void setTile(Tile tile) {
        if(tile != null) {
            this.tile = tile;
            rotation = tile.getRotationProperty();
            image.set(new Image(getClass().getResource(tile.getImagePath()).toExternalForm()));
            image.bind(Bindings.createObjectBinding(() -> {
                String path = tile.getImagePath();
                return new Image(getClass().getResource(path).toExternalForm());
            }, tile.getImagePathProperty()));
        }
    }

    public TileViewModel(ObjectProperty<Tile> tile) {
        setTile(tile.get());
        tile.addListener((_, _, newTile) -> {
            setTile(newTile);
        });
    }

}
