package app.viewmodels;

import app.model.*;
import app.utils.Position;
import app.view.BoardSelector;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import java.util.function.Consumer;

import static app.view.AbstractBoardView.DISPLAYED_GRID_SIZE;

public class BoardSelectorViewModel implements AiPlayersActionsListener {
    private static double DELAY = 0.5;

    private BoardViewModel boardViewModel;
    private Table table;

    private ObjectProperty<Tile> tile = new SimpleObjectProperty<>();
    private BooleanProperty isActive = new SimpleBooleanProperty(false);
    private ObjectProperty<Position> onViewPosition;
    private ObjectProperty<Position> onTablePosition;// = new SimpleObjectProperty<>();
    private ObjectProperty<Tile> onViewTile  = new SimpleObjectProperty<>();
    private ObjectProperty<BoardSelector.Outline> outlineProperty = new SimpleObjectProperty<>(BoardSelector.Outline.NONE);

    public BooleanProperty getIsActive() {return isActive;}
    public ObjectProperty<Position> getOnViewPosition() {return onViewPosition;}
    public ObjectProperty<Position> getOnTablePosition() {return onTablePosition;}
    public ObjectProperty<Tile> getOnViewTile() {return onViewTile;}
    public ObjectProperty<Tile> getTile() {return tile;}
    public ObjectProperty<BoardSelector.Outline> getOutlineProperty() {return outlineProperty;}

    public BoardSelectorViewModel(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        this.table = boardViewModel.getTable();

        this.onViewPosition = boardViewModel.getOnViewPositionProperty();
        this.onTablePosition = boardViewModel.getOnTablePositionProperty();

        for(Player p : boardViewModel.getGame().getPlayers())
            if(p instanceof AiPlayer)
                ((AiPlayer) p).addAiPlayerActionListener(this);

        updateViewProperties();
    }

    private void updateViewProperties() {
        if(table.getTile(onTablePosition.get().x(), onTablePosition.get().y()) != null)
            onViewTile.set(null);
        else
            onViewTile.set(tile.get());

        if(table.canPlace(tile.get(), onTablePosition.get().x(), onTablePosition.get().y()))
            outlineProperty.set(BoardSelector.Outline.GREEN);
        else
            outlineProperty.set(BoardSelector.Outline.RED);
    }

    public void placeTile(Tile tile) {
        if(boardViewModel.getGame().getCurrentPlayer() instanceof AiPlayer)
            return;
        this.tile.set(tile);
        isActive.set(true);
        updateViewProperties();
        KeyboardManager.getInstance().register(keyEventHandler);
    }

    public void moveSelection(int x, int y) {
        setOnTablePosition(new Position(onTablePosition.get().x() + x, onTablePosition.get().y() + y));
    }

    private void setOnTablePosition(Position newOnTablePosition) {
        if(newOnTablePosition.x() < 0 || newOnTablePosition.x() >= Table.TABLE_DIMENSIONS || newOnTablePosition.y() < 0 || newOnTablePosition.y() >= Table.TABLE_DIMENSIONS)
            return;
        Position newOnViewPosition = new Position(onViewPosition.get().x() + newOnTablePosition.x() - onTablePosition.get().x(), onViewPosition.get().y() + newOnTablePosition.y() - onTablePosition.get().y());
        if (newOnViewPosition.x() > 0 && newOnViewPosition.x() < DISPLAYED_GRID_SIZE - 1 && newOnViewPosition.y() > 0 && newOnViewPosition.y() < DISPLAYED_GRID_SIZE - 1)
            onViewPosition.set(newOnViewPosition);
        onTablePosition.set(newOnTablePosition);
        updateViewProperties();
    }

    private final Consumer<KeyEvent> keyEventHandler = new Consumer<>() {
        @Override
        public void accept(KeyEvent event) {
            switch (event.getCode()) {
                case UP -> moveSelection(0, -1);
                case DOWN -> moveSelection(0, 1);
                case LEFT -> moveSelection(-1, 0);
                case RIGHT -> moveSelection(1, 0);
                case R -> tile.get().rotate();
                case SPACE ->  {
                    if(table.canPlace(tile.get(), onTablePosition.get().x(), onTablePosition.get().y())) {
                        KeyboardManager.getInstance().remove(this);
                        isActive.set(false);
                        updateViewProperties();
                        table.placeTile(tile.get(), onTablePosition.get().x(), onTablePosition.get().y());
                        return;
                    }
                }
            }
            updateViewProperties();
        }
    };

    private void rotateWithDelay(Tile tile, int remainingRotations, Runnable onDone) {
        if (remainingRotations <= 0) {
            onDone.run();
            return;
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(DELAY));
        pause.setOnFinished(event -> {
            tile.rotate();
            updateViewProperties();
            rotateWithDelay(tile, remainingRotations - 1, onDone);
        });
        pause.play();
    }

    private void moveToPositionWithDelay(Position targetPos, Runnable onDone) {
        Position currentPos = onTablePosition.get();
        if (currentPos.equals(targetPos)) {
            onDone.run();
            return;
        }

        final int dx, dy;
        if (currentPos.x() < targetPos.x()) { dx = 1; dy = 0;}
        else if (currentPos.x() > targetPos.x()) { dx = -1; dy = 0;}
        else if (currentPos.y() < targetPos.y())  { dx = 0; dy = 1;}
        else { dx = 0; dy = -1;}

        PauseTransition pause = new PauseTransition(Duration.seconds(DELAY));
        pause.setOnFinished(event -> {
            moveSelection(dx, dy);
            moveToPositionWithDelay(targetPos, onDone);
        });
        pause.play();
    }

    @Override
    public void placeTileAi(Tile tile, AiPlayer.PlaceTileMove move) {
        this.tile.set(tile);
        isActive.set(true);
        moveToPositionWithDelay(move.pos(), () -> rotateWithDelay(tile, move.rotation(), () -> {
            updateViewProperties();
            isActive.set(false);
            table.placeTile(tile, move.pos().x(), move.pos().y());
        }));
    }
}
