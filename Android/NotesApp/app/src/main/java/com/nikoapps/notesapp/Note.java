package com.nikoapps.notesapp;

public class Note {

    int noteID;
    String noteText;
    String timestamp;

    public Note(int noteID, String noteText, String timestamp) {
        this.noteID = noteID;
        this.noteText = noteText;
        this.timestamp = timestamp;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
