package app.model;

public class Cloister extends Area {

    public Cloister(Tile tile) {
        super(tile);
    }

    public boolean isFinished() {
        Tile t = null;
        for (Tile tile : tiles) {
            t = tile;
        }
        int x = t.getOnTablePosition().x();
        int y = t.getOnTablePosition().y();
        for (int xx = -1; xx <= 1; xx++) {
            for (int yy = -1; yy <= 1; yy++) {
                if (t.getTable().getTile(x + xx, y + yy) == null) {
                    return false;
                }
            }
        }
        return true;
    }
    public void finish() {
        Tile t = null;
        for (Tile tile : tiles) {
            t = tile;
        }
        if (t.getFollower() != null) {
            t.getFollower().getPlayer().addPoints(9);
        }
    }
}
