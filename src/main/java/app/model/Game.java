package app.model;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Game {

    private final ArrayList<Player> players;
    private final Box box;
    private final Table table;

    private int currentPlayerNumber;
    private final ObjectProperty<Player> currentPlayer = new SimpleObjectProperty<>();

    BooleanProperty hasEnded = new SimpleBooleanProperty(false);


    public Game(){
        players = new ArrayList<>();
        table = new Table(this);
        box = new Box(this);
    }

    public BooleanProperty getHasEndedProperty() {
        return hasEnded;
    }

    public ObjectProperty<Player> getCurrentPlayerProperty() {
        return currentPlayer;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(int nr) {
        players.remove(nr);
    }
    public void nextPlayer() {
        box.giveTile();
        currentPlayerNumber = (currentPlayerNumber + 1) % players.size();
        currentPlayer.set(players.get(currentPlayerNumber));
    }

    public void start() {
        currentPlayerNumber = 0;
        currentPlayer.set(players.get(currentPlayerNumber));
        box.giveTile();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Table getTable() {
        return table;
    }
    public Box getBox() {
        return box;
    }
}
