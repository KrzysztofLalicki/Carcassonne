package app.model;

import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final ArrayList<Player> players;
    private final Box box;
    private final Table table;
    private int currentPlayerNumber;
    BooleanProperty hasEnded = new SimpleBooleanProperty(false);

    public Game(){
        players = new ArrayList<>();
        table = new Table(this);
        box = new Box(this);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(int nr) {
        players.remove(nr);
    }
    public void nextPlayer() {
        currentPlayerNumber = (currentPlayerNumber + 1) % players.size();
        notifyCurrentPlayerChangeListeners();

        Tile tileToPlace = box.giveTile();
        notifyPlaceTileListeners(tileToPlace);
    }

    public void start() {
        currentPlayerNumber = 0;
        notifyCurrentPlayerChangeListeners();

        Tile tileToPlace = box.giveTile();
        notifyPlaceTileListeners(tileToPlace);
    }

    public void end() {
        hasEnded.set(true);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerNumber);
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Box getBox() {
        return box;
    }
    public Table getTable() {
        return table;
    }

    public BooleanProperty getHasEndedProperty() {
        return hasEnded;
    }

    private final List<GameActionListener> gameActionListeners = new ArrayList<>();
    public void addGameActionListener(GameActionListener listener) {
        gameActionListeners.add(listener);
    }
    public void notifyPlaceTileListeners(Tile tile) {
        for(GameActionListener listener : gameActionListeners)
            listener.placeTile(tile);
    }
    public void notifyPlaceFollowerListeners(Tile tile, Follower follower) {
        for(GameActionListener listener : gameActionListeners)
            listener.placeFollower(tile, follower);
    }

    private final List<GameStateChangeListener> gameStateChangeListeners = new ArrayList<>();
    public void addGameStateChangeListener(GameStateChangeListener listener) {
        gameStateChangeListeners.add(listener);
    }
    public void notifyCurrentPlayerChangeListeners() {
        for(GameStateChangeListener listener : gameStateChangeListeners)
            listener.onCurrentPlayerChange();
    }
}
