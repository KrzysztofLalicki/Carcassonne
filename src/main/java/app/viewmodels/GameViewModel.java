package app.viewmodels;

import app.model.Game;

public class GameViewModel {
    private BoardViewModel boardViewModel;

    public GameViewModel(Game game) {
        boardViewModel = new BoardViewModel(game.getTable());
    }

    public BoardViewModel getBoardViewModel() {
        return boardViewModel;
    }

}
