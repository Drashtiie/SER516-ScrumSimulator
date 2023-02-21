package se.bettercode.scrum;

/**
 * Manages the done, remaining and total StoryPoints for a Story
 */
public class StoryPointSet {

    private StoryPoint done;
    private StoryPoint remaining;
    private StoryPoint total;

    protected StoryPointSet(int totalPoints) {
        this.done = new StoryPoint(0);
        this.remaining = new StoryPoint(totalPoints);
        this.total = new StoryPoint(totalPoints);
    }

    protected void apply(int points) {
        if (points > remaining.getPoints()) {
            throw new IllegalArgumentException("Can not apply more points than remaining points.");
        }

        done.add(points);
        remaining.subtract(points);
    }

    protected StoryPoint getDone() {
        return done;
    }

    protected StoryPoint getRemaining() {
        return remaining;
    }

    protected StoryPoint getTotal() {
        return total;
    }


}
