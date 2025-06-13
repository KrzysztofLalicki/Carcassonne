package app.model;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.max;

public class Road extends Area {

    private int ends;

    public Road(Tile tile) {
        super(tile);
        ends = 0;
    }

    public void mergeWith(Area area) {
        super.mergeWith(area);
        if (area instanceof Road road) {
            ends += road.ends;
            if (road == this) {
                ends = 2;
            }
        }
    }

    public void end() {
        ends++;
    }
    public boolean isFinished() {
        return ends == 2;
    }
    public void finish() {
        if (followers.isEmpty()) {
            return;
        }
        HashMap<Player, Integer> owners = new HashMap<>();
        for (Follower follower : followers) {
            if (owners.containsKey(follower.getPlayer())) {
                owners.replace(follower.getPlayer(), owners.get(follower.getPlayer()) + 1);
            }
            else {
                owners.put(follower.getPlayer(), 1);
            }
        }
        int max = max(owners.values());
        for (Map.Entry<Player, Integer> entry : owners.entrySet()) {
            if (entry.getValue() == max) {
                entry.getKey().addPoints(tiles.size());
            }
        }
        for (Segment segment : segments) {
            segment.removeFollower();
        }
        followers.clear();
    }
}
