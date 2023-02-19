package se.bettercode.scrum.backlog;

import lombok.Data;

@Data
public class BurnupDay {

    private int day, total, done;

    public BurnupDay(int day, int total, int done) {
        if (done > total) {
            throw new IllegalArgumentException("done " + done + " must not be larger than total " + total);
        }
        this.day = day;
        this.total = total;
        this.done = done;
    }

}
