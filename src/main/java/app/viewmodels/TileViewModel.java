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
    private final static String TILE_IMAGE_DIRECTORY = "/app/img/tiles/";
    private final static String TILE_IMAGE_EXTENSION = ".png";

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
        this.tile = tile;
        if(tile != null) {
            rotation = tile.getRotationProperty();
            image.bind(Bindings.createObjectBinding(() -> {
                String path = TILE_IMAGE_DIRECTORY + tile.getSymbolProperty().get() + TILE_IMAGE_EXTENSION;
                return new Image(getClass().getResource(path).toExternalForm());
            }, tile.getSymbolProperty()));
        }
        else {
            if(image.isBound())
                image.unbind();
            image.set(null);
        }
    }

    public TileViewModel(ObjectProperty<Tile> tile) {
        setTile(tile.get());
        tile.addListener((_, _, newTile) -> setTile(newTile));
    }

}
