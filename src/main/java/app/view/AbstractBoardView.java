package app.view;

import app.viewmodels.BoardSelectorViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import static app.view.TileView.TILE_SIZE;

public abstract class AbstractBoardView extends GridPane {
    public static final int DISPLAYED_GRID_SIZE = 11;

    public static final IntegerProperty BOARD_SIZE = new SimpleIntegerProperty();
    static {
        BOARD_SIZE.bind(TILE_SIZE.multiply(DISPLAYED_GRID_SIZE));
    }


    public AbstractBoardView() {
        setHgap(0);
        setVgap(0);

        setAlignment(Pos.CENTER);

//        heightProperty().addListener((obs, oldVal, newVal) -> {
//            setMinWidth(Math.round(newVal.doubleValue()));
//            setMaxWidth(Math.round(newVal.doubleValue()));
//        });

        minHeightProperty().bind(BOARD_SIZE);
        prefHeightProperty().bind(BOARD_SIZE);
        maxHeightProperty().bind(BOARD_SIZE);
        minWidthProperty().bind(BOARD_SIZE);
        prefWidthProperty().bind(BOARD_SIZE);
        maxWidthProperty().bind(BOARD_SIZE);

        for (int i = 0; i < DISPLAYED_GRID_SIZE; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.minWidthProperty().bind(TILE_SIZE);
            column.prefWidthProperty().bind(TILE_SIZE);
            column.maxWidthProperty().bind(TILE_SIZE);
            column.setPercentWidth(100.0 / DISPLAYED_GRID_SIZE);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < DISPLAYED_GRID_SIZE; i++) {
            RowConstraints row = new RowConstraints();
            row.minHeightProperty().bind(TILE_SIZE);
            row.prefHeightProperty().bind(TILE_SIZE);
            row.maxHeightProperty().bind(TILE_SIZE);
            getRowConstraints().add(row);
        }
    }

    @Override
    public void add(javafx.scene.Node node, int i, int i1) {
        setFillHeight(node, true);
        setFillWidth(node, true);
        setHgrow(node, Priority.ALWAYS);
        setVgrow(node, Priority.ALWAYS);
        super.add(node, i, i1);
    }
}
