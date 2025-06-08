package app.model;

public interface GameStateChangeListener {
    default void onCurrentPlayerChange() {}
    default void onGameEnd() {}
}
