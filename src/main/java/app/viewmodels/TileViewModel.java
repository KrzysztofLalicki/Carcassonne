package app.viewmodels;

import app.model.Tile;
import app.view.TileView;
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
    ObjectProperty<TileView.Outline> outline = new SimpleObjectProperty<>(TileView.Outline.NONE);
    public void setOutline(TileView.Outline outline) {
        this.outline.set(outline);
    }
    public ObjectProperty<TileView.Outline> getOutlineProperty() {
        return outline;
    }

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
