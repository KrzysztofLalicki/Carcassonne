package app.model;

public class Cloister extends Area {

    int surroundings;

    public Cloister(Tile tile) {
        super(tile);
        surroundings = 1;
    }
}
