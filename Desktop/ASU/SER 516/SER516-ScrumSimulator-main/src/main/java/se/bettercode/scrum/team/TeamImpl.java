package se.bettercode.scrum.team;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeamImpl implements Team {

    private StringProperty name;
    private IntegerProperty velocity;

    private IntegerProperty workInProgressLimit;

    public TeamImpl(String name, int velocity) {
        this(name, velocity, 1);
    }

    public TeamImpl(String name, int velocity, int workInProgressLimit) {
        if (workInProgressLimit < 1) {
            throw new IllegalArgumentException("workInProgressLimit must be 1 or greater.");
        }
        this.name = new SimpleStringProperty(name);
        this.velocity = new SimpleIntegerProperty(velocity);
        this.workInProgressLimit = new SimpleIntegerProperty(workInProgressLimit);
    }

    @Override
    public final StringProperty nameProperty() {
        return this.name;
    }

    @Override
    public final void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public final IntegerProperty velocityProperty() {
        return velocity;
    }

    @Override
    public final void setVelocity(int velocity) {
        this.velocity.set(velocity);
    }

    @Override
    public int getWorkInProgressLimit() {
        return workInProgressLimit.get();
    }

    @Override
    public IntegerProperty workInProgressLimitProperty() {
        return workInProgressLimit;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name=" + name.getValue() +
                ", velocity=" + velocity.get() +
                ", WIP limit=" + workInProgressLimit.get() +
                '}';
    }
}
