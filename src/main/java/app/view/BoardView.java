package app.view;

import app.utils.Position;
import app.viewmodels.BoardSelectorViewModel;
import app.viewmodels.BoardViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardView extends StackPane {
    public ObjectProperty<Position> onViewPosition;
    public ObjectProperty<Position> onTablePosition;

    public BoardView(BoardViewModel boardViewModel) {
        onViewPosition = boardViewModel.getOnViewPositionProperty();
        onTablePosition = boardViewModel.getOnTablePositionProperty();

        TableView tableView = new TableView(boardViewModel.getTableViewModel());
        BoardSelector boardSelector = new BoardSelector(boardViewModel.getBoardSelectorViewModel());

        getChildren().add(tableView);
        getChildren().add(boardSelector);

        setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
        ));
    }
}
