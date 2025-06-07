package app.view;

import app.model.Tile;
import app.utils.Position;
import app.viewmodels.BoardSelectorViewModel;
import app.viewmodels.TileViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    BoardSelectorViewModel boardSelectorViewModel;

    public BoardSelector(BoardSelectorViewModel boardSelectorViewModel) {
        super();

        this.boardSelectorViewModel = boardSelectorViewModel;

        boardSelectorViewModel.onViewPosition.addListener((_, _, _) -> update());
        boardSelectorViewModel.onTablePosition.addListener((_, _, _) -> update());
        boardSelectorViewModel.onViewTile.addListener((_, _, _) -> update());
        boardSelectorViewModel.outlineProperty.addListener((_, _, _) -> update());

        update();
    }

    private void update() {
        getChildren().clear();
        if(boardSelectorViewModel.onViewPosition.get() != null)
            add(selectedTileView(boardSelectorViewModel.onViewTile.get(), boardSelectorViewModel.outlineProperty.get()), boardSelectorViewModel.onViewPosition.get().x(), boardSelectorViewModel.onViewPosition.get().y());
    }

    private StackPane selectedTileView(Tile tile, Outline outline) {
        StackPane selectedTileView = new StackPane();

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