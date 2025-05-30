package app.view;

import app.model.Table;
import app.utils.Position;
import app.model.Tile;
import app.viewmodels.BoardViewModel;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import static app.viewmodels.BoardViewModel.DISPLAYED_GRID_SIZE;

public class BoardView extends GridPane {
    private final TileView[][] tiles;
    private final BoardViewModel boardViewModel;



    public BoardView(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];

        final Position onTablePosition = boardViewModel.getOnTablePosition();
        final Position onViewPosition = boardViewModel.getOnViewPosition();
        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++) {
                if(xTable == 71 && yTable == 71)
                    System.out.println("dupa");
                tiles[xView][yView] = new TileView(boardViewModel.getTileViewModel(xTable, yTable));
                this.add(tiles[xView][yView], xView, yView);
            }


        boardViewModel.getOnViewPositionProperty().addListener(_ -> updateTilesViewModels());
        boardViewModel.getOnTablePositionProperty().addListener(_ -> updateTilesViewModels());
    }

    private void updateTilesViewModels() {
        final Position onTablePosition = boardViewModel.getOnTablePosition();
        final Position onViewPosition = boardViewModel.getOnViewPosition();

        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++)
                tiles[xView][yView].setTileViewModel(boardViewModel.getTileViewModel(xTable, yTable));

    }



//    private void updateTilesImages() {
//        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
//            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++) {
//                try {
//                    tiles[xView][yView].setTileImage(new Image(getClass().getResource(table[xTable][yTable].getImagePath()).toExternalForm()));
//                    tiles[xView][yView].setRotate(table[xTable][yTable].getRotation());
//                } catch(NullPointerException | IndexOutOfBoundsException e) {
//                    tiles[xView][yView].setTileImage(null);
//                }
//            }
//    }
//
//    public void setSelectionOutline(TileView.Outline outline) {
//        this.outline = outline;
//        tiles[onViewPosition.x()][onViewPosition.y()].setOutline(outline);
//    }
//
//    public void setImageOnSelectedTile(Tile tile){
//        try {
//            tiles[onViewPosition.x()][onViewPosition.y()].setTileImage(new Image(getClass().getResource(tile.getImagePath()).toExternalForm()));
//            tiles[onViewPosition.x()][onViewPosition.y()].setRotate(tile.getRotation());
//        } catch(NullPointerException | IndexOutOfBoundsException e) {
//            tiles[onViewPosition.x()][onViewPosition.y()].setTileImage(null);
//        }
//    }
//
//
}
