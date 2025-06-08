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

    public void mergeWith(Road road) {
        super.mergeWith(road);
        ends += road.ends;
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
            owners.put(follower.getPlayer(), owners.getOrDefault(follower.getPlayer(), 1));
        }
        int max = max(owners.values());
        for (Map.Entry<Player, Integer> pair : owners.entrySet()) {
            if (pair.getValue() == max) {
                pair.getKey().addPoints(segments.size());
            }
        }
        for (Segment segment : segments) {
            segment.removeFollower();
        }
        followers.clear();
    }
}
