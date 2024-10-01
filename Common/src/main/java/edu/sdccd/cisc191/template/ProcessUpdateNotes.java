package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ProcessUpdateNotes
{
    /**
     * Reads UpdateNotes.txt file using a BufferedReader.
     * @return the update notes in the form of a string with next lines.
     */
    public String readTextFile()
    {
        StringBuilder updateNotes = new StringBuilder();
        URL resource = getClass().getClassLoader().getResource("UpdateNotes.txt");

        if (resource == null)
        {
            return "Could not load patch notes!";
        }

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                updateNotes.append(line).append("\n");
            }
            bufferedReader.close();
            return updateNotes.toString();
        }
        catch (IOException e)
        {
            return "Could not find patch notes!";
        }
    }
}
