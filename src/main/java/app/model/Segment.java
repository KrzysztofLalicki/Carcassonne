package app.model;

public class Segment {

    private Area area;
    private Follower follower;

    public Segment(Area area) {
        this.area = area;
        area.addSegment(this);
        follower = null;
    }

    public void setArea(Area area) {
        this.area = area;
        area.addSegment(this);
    }
    public Area getArea() {
        return area;
    }
    public void addFollower(Follower follower) {
        this.follower = follower;
        area.addFollower(follower);
    }
}
