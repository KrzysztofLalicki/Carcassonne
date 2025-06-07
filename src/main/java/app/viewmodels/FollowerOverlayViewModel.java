package app.viewmodels;

import app.model.Follower;
import app.model.OnTileChangedListener;
import app.model.Tile;
import app.utils.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class FollowerOverlayViewModel implements OnTileChangedListener {
    private ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Position> followerPositionProperty = new SimpleObjectProperty<>();

    public ObjectProperty<Color> getColorProperty() {return colorProperty;}
    public ObjectProperty<Position> getFollowerPositionProperty() {return followerPositionProperty;}

    private Tile tile;;

    public FollowerOverlayViewModel(Tile tile) {
        this.tile = tile;
        tile.addOnTileChangedListener(this);
        updateProperties();
    }

    private Follower follower;
    public void placeFollower(Follower follower) {
        colorProperty.set(null);
        followerPositionProperty.set(null);
        boolean canPlaceFollower = false;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(tile.canPlace(i, j)) {
                    canPlaceFollower = true;
                    followerPositionProperty.set(new Position(i, j));
                }
        if(!canPlaceFollower)
            tile.placeFollower(null, null);
        colorProperty.set(Color.GREEN);

        this.follower = follower;
        KeyboardManager.getInstance().register(handleKeyEvent);
    }

    private final Consumer<KeyEvent> handleKeyEvent = new Consumer<KeyEvent>() {
        @Override
        public void accept(KeyEvent event) {
            Position positionChange = new Position(0, 0);
            switch (event.getCode()) {
                case UP -> positionChange = new Position(0, -1);
                case DOWN -> positionChange = new Position(0, 1);
                case LEFT -> positionChange = new Position(-1, 0);
                case RIGHT -> positionChange = new Position(1, 0);
                case S -> {
                    KeyboardManager.getInstance().remove(this);
                    tile.placeFollower(null, null);
                }
                case SPACE -> {
                    if (tile.canPlace(followerPositionProperty.get().x(), followerPositionProperty.get().y())) {
                        KeyboardManager.getInstance().remove(this);
                        tile.placeFollower(follower, followerPositionProperty.get());
                    }
                }
            }
            Position newPosition = new Position(followerPositionProperty.get().x() + positionChange.x(), followerPositionProperty.get().y() + positionChange.y());
            if(0 <= newPosition.x() && newPosition.x() < 3 && 0 <= newPosition.y() && newPosition.y() < 3) {
                followerPositionProperty.set(newPosition);
                if(tile.canPlace(followerPositionProperty.get().x(), followerPositionProperty.get().y()))
                    colorProperty.set(Color.GREEN);
                else
                    colorProperty.set(Color.RED);
            }
        }
    };

    private void updateProperties() {
        if(tile.getFollower() == null) {
            colorProperty.set(null);
            followerPositionProperty.set(null);
            return;
        }
        colorProperty.set(tile.getFollower().getPlayer().getColor());
        followerPositionProperty.set(tile.getFollower().getOnTilePosition());
    }

    @Override
    public void onFollowerChanged() {
        updateProperties();
    };

    public void unsubscribeTile() {
        tile.removeOnTileChangedListener(this);
    }
}
