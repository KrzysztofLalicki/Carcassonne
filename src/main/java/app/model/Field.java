package app.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.Collections.max;

public class Field extends Area {

    private final HashSet<City> cities;

    public Field(Tile tile) {
        super(tile);
        cities = new HashSet<>();
    }

    public void mergeWith(Area area) {
        super.mergeWith(area);
        if (area instanceof Field f && f != this) {
            cities.addAll(f.cities);
        }
    }

    public void addCity(City city) {
        cities.add(city);
    }
    public void finish() {
        if (followers.isEmpty()) {
            return;
        }
        int points = 0;
        for (City city : cities) {
            if (city.isFinished()) {
                points += 3;
            }
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
                entry.getKey().addPoints(points);
            }
        }
        for (Segment segment : segments) {
            segment.removeFollower();
        }
        followers.clear();
    }
}
