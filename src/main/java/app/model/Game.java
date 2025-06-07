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
        Tile tileToPlace = box.giveTile();
        Player currentPlayer = players.get(currentPlayerNumber);
        notifyOnPlaceTileListeners(tileToPlace);
//        currentPlayer.set(players.get(currentPlayerNumber));
    }

    public void start() {
        currentPlayerNumber = 0;
//        while(!box.isEmpty()) {
            Player currentPlayer = players.get(currentPlayerNumber);
            Tile tileToPlace = box.giveTile();
            notifyOnPlaceTileListeners(tileToPlace);
//        }
//        box.giveTile();
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

    private List<GameActionListener> gameStateListeners = new ArrayList<>();
    public void addGameStateListener(GameActionListener listener) {
        gameStateListeners.add(listener);
    }
    public void notifyOnPlaceTileListeners(Tile tile) {
        for(GameActionListener listener : gameStateListeners)
            listener.placeTile(tile);
    }
    public void notifyPlaceFollowerListeners(Tile tile, Follower follower) {
        for(GameActionListener listener : gameStateListeners)
            listener.placeFollower(tile, follower);
    }
}
