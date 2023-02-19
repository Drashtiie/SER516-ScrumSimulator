package se.bettercode.scrum.backlog;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurnupDayTest {

    @Test
    public void createBurnUpDay() {
        BurnupDay burnupDay = new BurnupDay(1, 10, 2);
        assertEquals(1, burnupDay.getDay());
        assertEquals(10, burnupDay.getTotal());
        assertEquals(2, burnupDay.getDone());
    }

    @Test(expected = IllegalArgumentException.class)
    public void totalMustNotBeLessThanDone() {
        BurnupDay burnupDay = new BurnupDay(1, 4, 5);
    }
}
