package se.bettercode.scrum.backlog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface User {
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
