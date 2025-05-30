package app.model;

public class Segment {

    private Area area;

    public Segment(Area area) {
        this.area = area;
        area.addSegment(this);
    }

    public void setArea(Area area) {
        this.area = area;
        area.addSegment(this);
    }
    public Area getArea() {
        return area;
    }
}
