package app.view;

import app.utils.Position;
import app.viewmodels.BoardViewModel;
import app.viewmodels.TableViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static app.model.Table.STARTING_TILE_POSITION;
import static app.view.AbstractBoardView.DISPLAYED_GRID_SIZE;

public class BoardView extends StackPane {
    public ObjectProperty<Position> onViewPosition;
    public ObjectProperty<Position> onTablePosition;

    private TableView tableView;
    private  BoardSelector boardSelector;

    public BoardView(BoardViewModel boardViewModel) {

        onViewPosition = boardViewModel.onViewPositionProperty;
        onTablePosition = boardViewModel.onTablePositionProperty;

        tableView = new TableView(boardViewModel.tableViewModel);
        boardSelector = new BoardSelector(boardViewModel.boardSelectorViewModel);

        getChildren().add(tableView);
        getChildren().add(boardSelector);

        setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
        ));


        setOnKeyPressed(event -> {
//            boardViewModel.onKeyPressed(event);
        });
        setFocusTraversable(true);
    }
}
