package se.bettercode.scrum.gui;

import org.junit.Before;
import org.junit.Test;
import se.bettercode.scrum.Story;

import static org.junit.Assert.*;

public class DingAudioClipTest {

    Story notDoneStory;
    Story doneStory;

    @Before
    public void setUp() throws Exception {
        notDoneStory = new Story(5, "Not done story");
        doneStory = new Story(3, "Done story");
        doneStory.workOnStory(3, 1);
    }

    @Test
    public void dingAudioClipCanBeCreated() {
        DingAudioClip dingAudioClip = new DingAudioClip();
        assertNotNull(dingAudioClip);
    }

}