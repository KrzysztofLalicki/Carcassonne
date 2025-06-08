package app.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final Color color;
    private int points = 0;
    private final Follower[] followers;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        followers = new Follower[7];
        for (int i = 0; i < 7; i++) {
            followers[i] = new Follower(this);
        }
    }

    public String getName() {return name;}
    public Color getColor() {
        return color;
    }

    public void addPoints(int points) {
        this.points += points;
        notifyOnScoreChangeListeners();
    }
    public int getPoints() {return points;}

    public int getFollowersNumber() {
        int res = 0;
        for (Follower follower : followers)
            if (follower.isFree())
                res++;
        return res;
    }

    public Follower getFollower() {
        for (Follower follower : followers) {
            if (follower.isFree()) {
                return follower;
            }
        }
        return null;
    }

    private final List<PlayerChangeListener> playerChangeListeners = new ArrayList<>();
    public void addPlayerChangeListener(PlayerChangeListener playerChangeListener) {
        playerChangeListeners.add(playerChangeListener);
    }
    public void notifyOnScoreChangeListeners() {
        for (PlayerChangeListener listener : playerChangeListeners)
            listener.onScoreChange();
    }
    void notifyOnFollowerNumberChangeListeners() {
        for (PlayerChangeListener listener : playerChangeListeners)
            listener.onFollowersNumberChange();
    }
}
