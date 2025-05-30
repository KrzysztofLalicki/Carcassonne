package app.model;

public class City extends Area {

    int pennants;

    public City(Tile tile) {
        super(tile);
        pennants = 0;
    }
}
