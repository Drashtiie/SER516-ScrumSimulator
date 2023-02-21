package se.bettercode.scrum.team;

import se.bettercode.utils.Selectable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SelectableTeams extends Selectable<Team> {

    public SelectableTeams() {
        Team cobras = new CobraTeam();
        Team smurfs = new SmurfTeam();


        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/java/se/bettercode/scrum/gui/Teams"));
            String line = reader.readLine();

            while (line != null) {

                line = reader.readLine();
                System.out.println(line);
                // read next line
                Team x = new TeamImpl(line, 26);
                put(x.getName(), x);


            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        put(cobras.getName(), cobras);
//        put(smurfs.getName(), smurfs);
    }

}