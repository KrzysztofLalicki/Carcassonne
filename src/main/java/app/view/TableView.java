package app.view;

import app.model.Table;
import app.utils.Position;
import app.viewmodels.TableViewModel;
import app.viewmodels.TileViewModel;
import javafx.beans.property.ObjectProperty;
import java.util.Map;

import static app.model.Table.TABLE_DIMENSIONS;

public class TableView extends AbstractBoardView {
    private Table table;
    private ObjectProperty<Position> onTablePosition;
    private ObjectProperty<Position> onViewPosition;
    private Map<Position, TileViewModel> tileViewModels;

    TableView(TableViewModel tableViewModel) {
        super();

        table = tableViewModel.getTable();
        onTablePosition = tableViewModel.getOnTablePositionProperty();
        onViewPosition = tableViewModel.getOnViewPositionProperty();
        tileViewModels = tableViewModel.getTileViewModels();
        onTablePosition.addListener((_, _, _) -> updateView());
        tableViewModel.tableChangedEvent().addListener((_, _, _) -> updateView());
        updateView();
    }


    public void updateView() {
        for(TileViewModel tileViewModel : tileViewModels.values())
            tileViewModel.unsubscribeTile();
        tileViewModels.clear();

        getChildren().clear();
        for(int xTable = onTablePosition.get().x() - onViewPosition.get().x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.get().y() - onViewPosition.get().y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++)
                if(xTable >= 0 && xTable < TABLE_DIMENSIONS && yTable >= 0 && yTable < TABLE_DIMENSIONS)
                    if(table.getTile(xTable, yTable) != null) {
                        TileViewModel tileViewModel = new TileViewModel(table.getTile(xTable, yTable));
                        tileViewModels.put(new Position(xTable, yTable), tileViewModel);
                        TileView tileView = new TileView(tileViewModel);
                        add(tileView, xView, yView);
                    }
    }
}
