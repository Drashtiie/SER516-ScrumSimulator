package se.bettercode;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class MainTest {

    private static final String EOL = System.getProperty("line.separator");

    @Test
    @Ignore("Cant get this to work consistently because of Threads")
    public void testMain() throws Exception {
        String[] args = {""};
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            Main.main(args);
        } finally {
            System.setOut(console);
        }
        assertEquals(String.format(
                        "Starting command line app.%n" +
                                "Running Sprint simulation with team Team{name=The Cobras, velocity=23} for Sprint \"First sprint\" for 10 days...%n" +
                                "Backlog{stories=[Story{points=3, pointsDone=0, status=TODO}, Story{points=5, pointsDone=0, status=TODO}, Story{points=8, pointsDone=0, status=TODO}, Story{points=5, pointsDone=0, status=TODO}, Story{points=1, pointsDone=0, status=TODO}], average lead time=0.0}%n" +
                                "Total backlog size is 22 points.%n" +
                                "Burning through backlog at 2 points per day.%n" +
                                "Finished command line app.%n", EOL, EOL, EOL, EOL, EOL, EOL),
                bytes.toString());
    }
}
