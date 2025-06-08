package app.view;

import app.model.Tile;
import app.utils.Position;
import app.viewmodels.BoardSelectorViewModel;
import app.viewmodels.TileViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class BoardSelector extends AbstractBoardView {
    public enum Outline {
        NONE(Color.TRANSPARENT),
        RED(Color.RED),
        GREEN(Color.GREEN);

        public final Color color;
        Outline(Color color) {
            this.color = color;
        }
    }

    private ObjectProperty<Position> onViewPosition;
    private ObjectProperty<Position> onTablePosition;
    private ObjectProperty<Outline> outline;
    private ObjectProperty<Tile> onViewTile;
    private BooleanProperty isActive;

    public BoardSelector(BoardSelectorViewModel boardSelectorViewModel) {
        super();

        onViewPosition = boardSelectorViewModel.getOnViewPosition();
        onTablePosition = boardSelectorViewModel.getOnTablePosition();
        outline = boardSelectorViewModel.getOutlineProperty();
        onViewTile = boardSelectorViewModel.getOnViewTile();
        isActive = boardSelectorViewModel.getIsActive();

        onViewPosition.addListener((_, _, _) -> updateView());
        onTablePosition.addListener((_, _, _) -> updateView());
        onViewTile.addListener((_, _, _) -> updateView());
        outline.addListener((_, _, _) -> updateView());
        isActive.addListener((_, _, _) -> updateView());

        updateView();
    }

    private void updateView() {
        getChildren().clear();
        if(onViewPosition.get() != null && isActive.get())
            add(selectedTileView(onViewTile.get(), outline.get()), onViewPosition.get().x(), onViewPosition.get().y());
    }

    public static StackPane selectedTileView(Tile tile, Outline outline) {
        StackPane selectedTileView = new StackPane();
        selectedTileView.minHeightProperty().bind(TileView.TILE_SIZE);
        selectedTileView.prefHeightProperty().bind(TileView.TILE_SIZE);
        selectedTileView.maxHeightProperty().bind(TileView.TILE_SIZE);
        selectedTileView.minWidthProperty().bind(TileView.TILE_SIZE);
        selectedTileView.prefWidthProperty().bind(TileView.TILE_SIZE);
        selectedTileView.maxWidthProperty().bind(TileView.TILE_SIZE);

        if(tile != null) {
            TileViewModel tileViewModel = new TileViewModel(tile);
            TileView tileView = new TileView(tileViewModel);
            selectedTileView.getChildren().add(tileView);
        }

        if(outline != null ) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(outline.color);
            rectangle.setStrokeWidth(5);
            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.widthProperty().bind(selectedTileView.widthProperty());
            rectangle.heightProperty().bind(selectedTileView.heightProperty());
            selectedTileView.getChildren().add(rectangle);
        }

        return selectedTileView;
    }
}