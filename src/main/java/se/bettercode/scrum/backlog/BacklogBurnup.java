package se.bettercode.scrum.backlog;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class BacklogBurnup {

    ListProperty<BurnupDay> burnupDays = new SimpleListProperty<BurnupDay>(FXCollections.observableList(new ArrayList<BurnupDay>()));

    public ObservableList<BurnupDay> getBurnupDays() {
        return burnupDays.get();
    }

    public ListProperty<BurnupDay> burnupDaysProperty() {
        return burnupDays;
    }

    void addDay(BurnupDay burnupDay) {
        burnupDays.add(burnupDay);
    }

}
