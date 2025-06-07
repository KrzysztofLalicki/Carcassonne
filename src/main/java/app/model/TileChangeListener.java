package app.model;

public interface TileChangeListener {
    public default void onRotationChanged() {};
    public default void onFollowerChanged() {};
}
