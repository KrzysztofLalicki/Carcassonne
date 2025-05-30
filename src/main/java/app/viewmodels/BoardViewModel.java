package app.viewmodels;

import app.model.Table;

public class BoardViewModel {
    private TileViewModel[][] tileViewModels = new TileViewModel[143][143];

    public BoardViewModel(Table table) {
        for(int i = 0; i < 143; i++)
            for(int j = 0; j < 143; j++)
                tileViewModels[i][j] = new TileViewModel(table.getTiles()[i][j]);
    }

    public TileViewModel getTileViewModel(int x, int y) {
        return tileViewModels[x][y];
    }

}
