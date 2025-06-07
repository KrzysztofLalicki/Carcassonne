package app.viewmodels;

import app.model.Follower;
import app.model.TileChangeListener;
import app.model.Tile;
import javafx.beans.property.*;

public class TileViewModel implements TileChangeListener {
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
