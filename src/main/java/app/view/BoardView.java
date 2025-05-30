package app.view;

import app.utils.Position;
import app.viewmodels.BoardViewModel;
import app.viewmodels.TileViewModel;
import javafx.scene.layout.GridPane;

import static app.viewmodels.BoardViewModel.DISPLAYED_GRID_SIZE;

public class BoardView extends GridPane {
    private final TileView[][] tiles;
    private final TileView selectionTileView;

    private final BoardViewModel boardViewModel;

    public BoardView(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;

        tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];
        selectionTileView = new TileView(boardViewModel.getSelectionTileViewModel());

        final Position onTablePosition = boardViewModel.getOnTablePosition();
        final Position onViewPosition = boardViewModel.getOnViewPosition();
        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++) {
                tiles[xView][yView] = new TileView(boardViewModel.getTileViewModel(xTable, yTable));
                this.add(tiles[xView][yView], xView, yView);
            }

        boardViewModel.getOnViewPositionProperty().addListener(_ -> updateTilesViewModels());
        boardViewModel.getOnTablePositionProperty().addListener(_ -> updateTilesViewModels());
        boardViewModel.getSelectionTileViewModel().getOutlineProperty().addListener(_ -> updateTilesViewModels());
    }

    private void updateTilesViewModels() {
        final Position onTablePosition = boardViewModel.getOnTablePosition();
        final Position onViewPosition = boardViewModel.getOnViewPosition();

        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++)
                tiles[xView][yView].setTileViewModel(boardViewModel.getTileViewModel(xTable, yTable));

        tiles[onViewPosition.x()][onViewPosition.y()].setTileViewModel(boardViewModel.getSelectionTileViewModel());
    }
}
