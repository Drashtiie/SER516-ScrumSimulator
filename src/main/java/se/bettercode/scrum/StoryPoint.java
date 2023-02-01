package se.bettercode.scrum;

public class StoryPoint {

    private int points;

    public StoryPoint(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be a negative value.");
        }
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void add(int points) {
        this.points += points;
    }

    public void add(StoryPoint points) {
        add(points.getPoints());
    }

    public void subtract(int points) {
        add(-points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryPoint that = (StoryPoint) o;

        if (this.getPoints() != that.getPoints()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return points;
    }

    @Override
    public String toString() {
        return points + "";
    }
}
