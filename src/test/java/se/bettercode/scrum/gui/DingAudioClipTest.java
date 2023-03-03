package se.bettercode.scrum.gui;

import org.junit.Before;
import org.junit.Test;
import se.bettercode.scrum.Story;

import static org.junit.Assert.*;

public class DingAudioClipTest {

<<<<<<< HEAD
    Story notDoneStory;
    Story doneStory;

    @Before
    public void setUp() throws Exception {
        notDoneStory = new Story(5, "Not done story", "New","FINISHED");
        doneStory = new Story(3, "Done story", "New","FINISHED");
        doneStory.workOnStory(3, 1);
    }

    @Test
    public void dingAudioClipCanBeCreated() {
        DingAudioClip dingAudioClip = new DingAudioClip();
        assertNotNull(dingAudioClip);
    }
=======
//    Story notDoneStory;
//    Story doneStory;
//
//    @Before
//    public void setUp() throws Exception {
//        notDoneStory = new Story(5, "Not done story", "New");
//        doneStory = new Story(3, "Done story", "New");
//        doneStory.workOnStory(3, 1);
//    }
//
//    @Test
//    public void dingAudioClipCanBeCreated() {
//        DingAudioClip dingAudioClip = new DingAudioClip();
//        assertNotNull(dingAudioClip);
//    }
>>>>>>> 119da7acd7ceb48f843f008914b8f204eb4a4978

}
