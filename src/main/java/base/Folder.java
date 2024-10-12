package base;

import java.util.*;

public class Folder implements Comparable<Folder>{
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

    @Override
    public int compareTo(Folder o) {
        return this.name.compareTo(o.name);
    }

    public void sortNotes() {
        Collections.sort(notes);
    }

    public List<Note> searchNotes(String keywords) {
        List<Note> matchedNotes = new ArrayList<>();

        String[] keywordArray = keywords.toLowerCase().split(" ");
        List<List<String>> groupArray = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        boolean previousKeywordIsOr = false;


        for (String keyword : keywordArray) {
            if (keyword.equals("or")) {
                previousKeywordIsOr = true;
            } else if (previousKeywordIsOr) {
                tempList.add(keyword);
                previousKeywordIsOr = false;
            } else {
                if (!tempList.isEmpty()) {
                    groupArray.add(new ArrayList<>(tempList));
                    tempList.clear();
                }
                tempList.add(keyword);
            }
        }

        groupArray.add(new ArrayList<>(tempList));


        for (Note note : notes) {
            String content = null;
            if (note instanceof TextNote)
                content = note.getTitle() + ((TextNote) note).getContent();
            else if (note instanceof ImageNote)
                content = note.getTitle();

            boolean allGroupsMatch = true;
            for (List<String> group : groupArray) {
                boolean groupMatches = false;
                for (String word : group) {
                    assert content != null;
                    if (content.toLowerCase().contains(word.toLowerCase())) {
                        groupMatches = true;
                        break;
                    }
                }
                if (!groupMatches) {
                    allGroupsMatch = false;
                    break;
                }
            }
            if (allGroupsMatch) {
                matchedNotes.add(note);
            }
        }
        return matchedNotes;
    }
}
