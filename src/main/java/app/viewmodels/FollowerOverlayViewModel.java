package app.viewmodels;

import app.model.Follower;
import app.model.TileChangeListener;
import app.model.Tile;
import app.utils.Position;
import javafx.animation.PauseTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.function.Consumer;

public class FollowerOverlayViewModel implements TileChangeListener {
    private static final double DELAY = 0.3;

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
        if (follower == null) {
            tile.placeFollower(null, null);
            return;
        }
        colorProperty.set(null);
        followerPositionProperty.set(null);
        boolean canPlaceFollower = false;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(tile.canPlace(i, j)) {
                    canPlaceFollower = true;
                    followerPositionProperty.set(new Position(i, j));
                }
        if(!canPlaceFollower) {
            tile.placeFollower(null, null);
            return;
        }
        colorProperty.set(Color.GREEN);

        this.follower = follower;

        KeyboardManager.getInstance().register(handleKeyEvent);
    }

    public void moveWithDelay(Position targetPos, Runnable onDone) {
        Position currentPos = followerPositionProperty.get();
        if (currentPos == null || currentPos.equals(targetPos)) {
            if (onDone != null) {
                onDone.run();
            }
            return;
        }

        final int dx, dy;

        if (currentPos.x() < targetPos.x()) { dx = 1; dy = 0; }
        else if (currentPos.x() > targetPos.x()) { dx = -1; dy = 0; }
        else if (currentPos.y() < targetPos.y()) { dx = 0; dy = 1; }
        else { dx = 0; dy = -1; }

        PauseTransition pause = new PauseTransition(Duration.seconds(DELAY));
        pause.setOnFinished(event -> {
            move(new Position(dx, dy));
            moveWithDelay(targetPos, onDone);
        });
        pause.play();
    }


    public void placeAiFollower(Follower follower, Position pos) {
        KeyboardManager.getInstance().clear();
        if(follower == null || pos == null) {
            tile.placeFollower(null, null);
            return;
        }

        colorProperty.set(null);
        followerPositionProperty.set(null);
        boolean canPlaceFollower = false;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(tile.canPlace(i, j)) {
                    canPlaceFollower = true;
                    followerPositionProperty.set(new Position(i, j));
                }
        if(!canPlaceFollower) {
            tile.placeFollower(null, null);
            return;
        }
        colorProperty.set(Color.GREEN);

        this.follower = follower;

        moveWithDelay(pos, () -> tile.placeFollower(follower, pos));
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
                    followerPositionProperty.set(null);
                    colorProperty.set(null);
                    KeyboardManager.getInstance().clear();
                    tile.placeFollower(null, null);
                    return;
                }
                case SPACE -> {
                    if (tile.canPlace(followerPositionProperty.get().x(), followerPositionProperty.get().y())) {
                        KeyboardManager.getInstance().clear();
                        tile.placeFollower(follower, followerPositionProperty.get());
                        return;
                    }
                }
            }
            move(positionChange);
        }
    };

    private void move(Position positionChange) {
        if(followerPositionProperty.get() != null) {
            Position newPosition = new Position(followerPositionProperty.get().x() + positionChange.x(), followerPositionProperty.get().y() + positionChange.y());
            if (0 <= newPosition.x() && newPosition.x() < 3 && 0 <= newPosition.y() && newPosition.y() < 3) {
                followerPositionProperty.set(newPosition);
                if (tile.canPlace(followerPositionProperty.get().x(), followerPositionProperty.get().y()))
                    colorProperty.set(Color.GREEN);
                else
                    colorProperty.set(Color.RED);
            }
        }
    }

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
