package app.view;

import app.viewmodels.BoardViewModel;
import javafx.scene.layout.GridPane;

import static app.viewmodels.BoardViewModel.DISPLAYED_GRID_SIZE;

public class BoardView extends GridPane {
    private final TileView[][] tiles;

    public BoardView(BoardViewModel boardViewModel) {
        tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];

        for (int i = 0; i < DISPLAYED_GRID_SIZE; i++)
            for (int j = 0; j < DISPLAYED_GRID_SIZE; j++) {
                tiles[i][j] = new TileView(boardViewModel.getOnTableTilesViewModelsProperties(i, j).get());
                this.add(tiles[i][j], i, j);
                final TileView tile = tiles[i][j];
                boardViewModel.getOnTableTilesViewModelsProperties(i, j).addListener((_, _, newViewModel) ->
                        tile.setTileViewModel(newViewModel));
            }

        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W, UP -> boardViewModel.moveSelection(0, -1);
                case S, DOWN -> boardViewModel.moveSelection(0, 1);
                case A, LEFT -> boardViewModel.moveSelection(-1, 0);
                case D, RIGHT -> boardViewModel.moveSelection(1, 0);
                case R -> boardViewModel.rotateNextTile();
                case SPACE ->  boardViewModel.placeTile();
            }
        });
        setFocusTraversable(true);
    }
}
