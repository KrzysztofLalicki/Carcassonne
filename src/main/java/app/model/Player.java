package app.model;

import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int points;
    public Player(String name) {
        this.name = name;
        points = 0;
    }
    public void rename(String name) {
        this.name = name;
    }
    public void addPoints(int points) {
        this.points += points;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    public int getPoints() {
        return points;
    }
}
