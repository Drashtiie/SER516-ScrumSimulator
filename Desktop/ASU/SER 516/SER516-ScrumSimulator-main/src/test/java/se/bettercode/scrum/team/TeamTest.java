package se.bettercode.scrum.team;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamTest {

    @Test
    public void testToString() {
        Team team = new TeamImpl("Dingos", 17);
        assertEquals("Team{name=Dingos, velocity=17, WIP limit=1}", team.toString());
    }

    @Test
    public void testDefaultVelocity() {
        Team team = new TeamImpl("Rhinos", 10);
        assertEquals(1, team.getWorkInProgressLimit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWIPLimit() {
        new TeamImpl("Monkeys", 20, 0);
    }
}
