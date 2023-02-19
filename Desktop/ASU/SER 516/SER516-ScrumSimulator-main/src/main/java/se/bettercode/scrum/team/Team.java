package se.bettercode.scrum.team;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface Team {
    StringProperty nameProperty();

    void setName(String name);

    String getName();

    IntegerProperty velocityProperty();

    void setVelocity(int velocity);

    int getWorkInProgressLimit();

    IntegerProperty workInProgressLimitProperty();

    @Override
    String toString();
}
