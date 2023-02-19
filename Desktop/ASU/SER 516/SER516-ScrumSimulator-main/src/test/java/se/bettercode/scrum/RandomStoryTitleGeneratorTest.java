package se.bettercode.scrum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomStoryTitleGeneratorTest {

    RandomStoryTitleGenerator generator = new RandomStoryTitleGenerator();

    @Test
    public void generateOne()  {
        String result = generator.generateOne();
        assertNotNull(result);
    }

    @Test
    public void generateTen()  {
        ArrayList<String> result = generator.generate(10);
        assertEquals(10, result.size());
    }

    @Test
    public void allGeneratedTitlesAreUnique() {
        ArrayList<String> result = generator.generate(10);
        Set<String> resultSet = new HashSet<>(result);
        assertEquals(10, resultSet.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantGenerate1000Titles() {
        generator.generate(1000);
    }

}
