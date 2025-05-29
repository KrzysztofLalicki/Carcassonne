package app.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {

    private final String name;
    private Color color;
    private int points;
    private final ArrayList<Follower> followers;

    public Player(String name) {
        this.name = name;
        points = 0;
        followers = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            followers.add(new Follower(this));
        }
    }

    /* PROBABLY USELESS
    public void rename(String name) {
        this.name = name;
    }
    */
    public String getName() {
        return name;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public void addPoints(int points) {
        this.points += points;
    }
    public int getPoints() {
        return points;
    }
    public boolean hasFollower() {
        for (Follower follower : followers) {
            if (follower.isFree()) {
                return true;
            }
        }
        return false;
    }
    public Follower getFollower() {
        for (Follower follower : followers) {
            if (follower.isFree()) {
                return follower;
            }
        }
        return null;
    }

    public void doTurn(Tile tile, Table table) {}
}
