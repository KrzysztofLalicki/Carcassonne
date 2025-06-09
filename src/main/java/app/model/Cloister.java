package app.model;

public class Cloister extends Area {

    int surroundings;

    public Cloister(Tile tile) {
        super(tile);
        surroundings = 1;
    }

    public void surround() {
        surroundings++;
    }

    public boolean isFinished() {
        System.out.println("surroundings " + surroundings);
        return surroundings == 9;
    }
    public void finish() {
        Tile t = null;
        for (Tile tile : tiles) {
            t = tile;
        }
        if (t.getFollower() != null) {
            t.getFollower().getPlayer().addPoints(surroundings);
        }
        t.getSegments()[1][1].removeFollower();
        followers.clear();
    }
}
