package app.model;

public interface OnTileChangedListener {
    public default void onRotationChanged() {};
    public default void onFollowerChanged() {};
}
