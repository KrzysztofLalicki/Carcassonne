package app.viewmodels;

import app.model.Follower;
import app.model.OnTileChangedListener;
import app.model.Tile;
import app.utils.Position;
import app.view.TileView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.function.Consumer;

public class TileViewModel implements OnTileChangedListener {
    private Tile tile;
    private IntegerProperty rotation = new SimpleIntegerProperty();
    private FollowerOverlayViewModel followerOverlayViewModel;
    public TileViewModel(Tile tile) {
        this.tile = tile;
        tile.addOnTileChangedListener(this);
        followerOverlayViewModel = new FollowerOverlayViewModel(tile);
        rotation.set(tile.getRotation());
    }

    public IntegerProperty getRotationProperty() {
        return rotation;
    }

    public String getSymbol() {
        return tile.getSymbol();
    }

    public FollowerOverlayViewModel getFollowerOverlayViewModel() {
        return followerOverlayViewModel;
    }

    public void placeFollower(Follower follower) {
        followerOverlayViewModel.placeFollower(follower);
    }

    @Override
    public void onRotationChanged() {
        rotation.set(tile.getRotation());
    };

    public void unsubscribeTile() {
        followerOverlayViewModel.unsubscribeTile();
        tile.removeOnTileChangedListener(this);
    }
}
