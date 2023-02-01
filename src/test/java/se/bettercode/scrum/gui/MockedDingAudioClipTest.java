package se.bettercode.scrum.gui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import se.bettercode.scrum.Story;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockedDingAudioClipTest {

    @Mock
    DingAudioClip dingAudioClipMock;

    Story notDoneStory;
    Story doneStory;

    @Before
    public void setUp() throws Exception {
        notDoneStory = new Story(5, "Not done story");
        doneStory = new Story(3, "Done story");
        doneStory.workOnStory(3, 1);
    }

    @Test
    public void notDoneStoryWillNotPlaySound() {
        dingAudioClipMock.playIfDone(notDoneStory);
        Mockito.verify(dingAudioClipMock, times(0)).play();
    }

    public void doneStoryWillPlaySoundsEqualToNumberOfStoryPoints() {
        dingAudioClipMock.playIfDone(doneStory);
        int expectedPlayTimes = doneStory.getTotalPoints().getPoints();
        Mockito.verify(dingAudioClipMock, times(expectedPlayTimes)).play();
    }
}
