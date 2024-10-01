package edu.sdccd.cisc191.template;

public class UpdateNotesResponse
{
    public String getUpdateNotes()
    {
        ProcessUpdateNotes notes = new ProcessUpdateNotes();
        return notes.readTextFile();
    }
}
