package app.model;

import app.utils.Position;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.model.Table.TABLE_DIMENSIONS;

public class AiPlayer extends Player implements GameActionListener {
    private static String[] names = { "Zbigniew z Kotliny", "Jagoda z Zamczyska", "Mistrz Maciej", "Rycerz Tadeusz", "Czarodziejka Bożena", "Biskup Wiesław", "Szymon bez Ziemi", "Dobrosław Budowniczy", "Halina z Wieży", "Wojmir Płotkarz", "Kazimierz Murarz", "Hrabia Eugeniusz", "Otylia z Grodziska", "Grzegorz Mnich", "Zofia Zakonodajka", "Mikołaj z Rynku", "Wanda Kartografka", "Janusz Brukarzu" };
    private static Random random = new Random();

    private Game game;

    public AiPlayer(Color color, Game game) {
        this.color = color;
        while(name == null) {
            name = names[random.nextInt(names.length)];
            if(game.getPlayers().stream().map(Player::getName).anyMatch(name::equals))
                name = null;
        }
        this.game = game;
    }

    public record PlaceTileMove (int rotation, Position pos) {}
    @Override
    public void placeTile(Tile tile) {
        if(game.getCurrentPlayer() != this)
            return;
        List<PlaceTileMove> allowedMoves = new ArrayList<>();
        for(int i = 0 ; i < 4; i++) {
            for(int x = 0; x < TABLE_DIMENSIONS; x++)
                for(int y = 0; y < TABLE_DIMENSIONS; y++)
                    if(game.getTable().canPlace(tile, x, y))
                        allowedMoves.add(new PlaceTileMove(i, new Position(x, y)));
            tile.rotate();
        }
        PlaceTileMove move = allowedMoves.get(random.nextInt(allowedMoves.size()));
        notifyOnPlaceTileAiListener(tile, move);
    }

    @Override
    public void placeFollower(Tile tile, Follower follower) {

    }

    List<AiPlayersActionsListener> aiPlayersActionsListeners = new ArrayList<>();
    public void addAiPlayerActionListener(AiPlayersActionsListener listener) {
        aiPlayersActionsListeners.add(listener);
    }
    public void notifyOnPlaceTileAiListener(Tile tile, PlaceTileMove move) {
        for(AiPlayersActionsListener listener : aiPlayersActionsListeners)
            listener.placeTileAi(tile, move);
    }
}
