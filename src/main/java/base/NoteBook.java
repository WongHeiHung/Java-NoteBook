package base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NoteBook {
    private ArrayList<Folder> folders;

    public NoteBook(){
        this.folders = new ArrayList<Folder>();
    }

    public boolean insertNote(String folderName, Note note){
        //TODO:
        boolean folderExist = false;
        Folder folderFound = null;
        if(folders != null) {
            for (Folder folder : folders) {
                if (folder.equals(folderName)) {
                    folderExist = true;
                    folderFound = folder;
                    break;
                }
            }
        }
        if(!folderExist){
            folderFound = new Folder(folderName);
            this.folders.add(folderFound);
        }

        boolean noteExist = false;
        Note noteFound = null;
        if(folderFound.getNotes() != null) {
            for (Note note_i : folderFound.getNotes()) {
                if(note_i != null) {
                    if (note_i.equals(note)) {
                        noteExist = true;
                        noteFound = note_i;
                        break;
                    }
                }
            }
        }
        if(noteExist){
            System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed.");
            return false;
        }
        else{
            noteFound = note;
            folderFound.addNote(noteFound);
            return true;
        }
    }

    public boolean createTextNote(String folderName, String title){
        TextNote note = new TextNote(title);
        return insertNote(folderName, note);
    }

    public boolean createTextNote(String folderName, String title, String content){
        TextNote note = new TextNote(title, content);
        return insertNote(folderName, note);
    }

    public boolean createImageNote(String folderName, String title){
        ImageNote note = new ImageNote(title);
        return insertNote(folderName, note);
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void sortFolders() {
        for(Folder folder : folders){
            folder.sortNotes();
        }
        Collections.sort(folders);
    }

    public List<Note> searchNotes(String keywords) {
        List<Note> notes = new ArrayList<>();
        for(Folder folder : folders){
            notes.addAll(folder.searchNotes(keywords));
        }
        return notes;
    }
}
