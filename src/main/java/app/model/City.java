package app.model;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.max;

public class City extends Area {

    int pennants;
    int holes;

    public City(Tile tile) {
        super(tile);
        pennants = 0;
        holes = 0;
    }

    public void mergeWith(Area area) {
        super.mergeWith(area);
        if (area instanceof City c) {
            pennants += c.pennants;
            holes += c.holes - 2;
        }
    }

    public void addPennant() {
        pennants++;
    }
    public void addHole() {
        holes++;
    }
    public boolean isFinished() {
        return holes == 0;
    }
    public void finish() {
        if (followers.isEmpty()) {
            return;
        }
        HashMap<Player, Integer> owners = new HashMap<>();
        for (Follower follower : followers) {
            owners.put(follower.getPlayer(), owners.getOrDefault(follower.getPlayer(), 1));
        }
        int max = max(owners.values());
        for (Map.Entry<Player, Integer> pair : owners.entrySet()) {
            if (pair.getValue() == max) {
                pair.getKey().addPoints(tiles.size() + pennants);
                if (isFinished()) {
                    pair.getKey().addPoints(tiles.size() + pennants);
                }
            }
        }
        for (Segment segment : segments) {
            segment.removeFollower();
        }
        followers.clear();
    }
}
