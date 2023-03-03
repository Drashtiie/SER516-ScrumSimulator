package se.bettercode.scrum;

public class StoryDays {

    private int startedDay;
    private int doneDay;

    protected int getStartedDay() {
        return startedDay;
    }

    protected void setStartedDay(int startedDay) {
        if (startedDay < 0) {
            throw new IllegalArgumentException("startedDay must not be negative");
        }
        this.startedDay = startedDay;
    }

    protected int getDoneDay() {
        return doneDay;
    }

    protected void setDoneDay(int doneDay) {
        if (doneDay < startedDay) {
            throw new IllegalArgumentException("doneDay must not be less than startedDay");
        }
        this.doneDay = doneDay;
    }

    protected double getLeadTime() {
        return doneDay - startedDay;
    }
}
