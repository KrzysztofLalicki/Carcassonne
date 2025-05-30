package app.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Player {

    private final String name;
    private ObjectProperty<Color> color;
    private int points;
    private final Follower[] followers;

    public Player(String name) {
        this.name = name;
        points = 0;
        this.color = new SimpleObjectProperty<>(Color.TRANSPARENT);
        followers = new Follower[7];
        for (Follower follower : followers) {
            follower = new Follower(this);
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
        this.color.set(color);
    }
    public Color getColor() {
        return this.color.get();
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
