package app.model;

import app.utils.Position;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.model.Box.NUMBER_OF_TILES;
import static app.model.Table.TABLE_DIMENSIONS;

public class AiPlayer extends Player {
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
    public PlaceTileMove getTilePlacement(Tile tile) {
        List<PlaceTileMove> allowedMoves = new ArrayList<>();
        for(int i = 0 ; i < 4; i++) {
            for(int x = 0; x < TABLE_DIMENSIONS; x++)
                for(int y = 0; y < TABLE_DIMENSIONS; y++)
                    if(game.getTable().canPlace(tile, x, y))
                        allowedMoves.add(new PlaceTileMove(i, new Position(x, y)));
            tile.rotate();
        }
        return allowedMoves.get(random.nextInt(allowedMoves.size()));
    }

    public Position getFollowerPlacement(Tile tile, Follower follower) {
        int numberOfPlayers = game.getPlayers().size();
        int numberOfTilesOnTable = 0;
        for(int x = 0; x < TABLE_DIMENSIONS; x++)
            for(int y = 0; y < TABLE_DIMENSIONS; y++)
                if(game.getTable().getTile(x, y) != null)
                    numberOfTilesOnTable++;
        int tilesToPlace = (NUMBER_OF_TILES - numberOfTilesOnTable) / numberOfPlayers;
        int freeFollowers = this.getFollowersNumber();

        if(Math.min((double)freeFollowers/tilesToPlace, 1.0) < random.nextDouble()) {
            return null;
        }

        List<Position> possiblePlacements = new ArrayList<>();
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(tile.canPlace(i, j))
                    possiblePlacements.add(new Position(i, j));

        if(possiblePlacements.isEmpty()) {
            return null;
        }

        return possiblePlacements.get(random.nextInt(possiblePlacements.size()));
    }
}
