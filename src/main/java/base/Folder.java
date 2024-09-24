package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
    private ArrayList<Note> notes;
    private String name;

    public Folder(String name){
        this.notes = new ArrayList<Note>();
        this.name = name;
    }

    public void addNote(Note note){
        this.notes.add(note);
    }
    public String getName() {
        return name;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String toString(){
        int nText = 0;
        int nImage = 0;

        //TODO:
        for(Note note : notes){
            if(note instanceof ImageNote)
                nImage++;
            if(note instanceof TextNote)
                nText++;
        }
        return name + ":" + nText + ":" + nImage;
    }

    public boolean equals(String filename) {
        return Objects.equals(filename, this.name);
    }
}
