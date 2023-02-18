package se.bettercode.scrum;


import org.junit.Before;
import org.junit.Test;
import se.bettercode.scrum.backlog.Backlog;
import se.bettercode.scrum.backlog.SmallBacklog;
import se.bettercode.scrum.team.Team;
import se.bettercode.scrum.team.TeamImpl;

import static org.junit.Assert.assertEquals;

public class SprintTest {

    Sprint sprint;
    Team team;
    Backlog backlog;

    @Before
    public void setUp() {
        team = new TeamImpl("Rock Stars", 23);
        backlog = new SmallBacklog();
        sprint = new Sprint("First Sprint", 10, team, backlog);
    }

    @Test
    public void sprintIsNotRunningWhenCreated() {
        assertEquals(false, sprint.getRunning());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantRunSprintWithoutTeamAndBacklog() {
        Sprint badSprint = new Sprint("Bad Sprint", 5, null, null);
        badSprint.runSprint();
    }
}
