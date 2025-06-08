package app.model;

public interface TileChangeListener {
    default void onRotationChanged() {};
    default void onFollowerChanged() {};
}
