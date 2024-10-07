package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>{
    private Date date;
    private String title;

    static long counter = 1L;

    public Note(String title){
        this.title = title;
        this.date = new Date(counter);
        counter++;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Note note = (Note) o;
        return Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public int compareTo(Note o) {
        if(this instanceof TextNote && o instanceof ImageNote)
            return -1;
        if(this instanceof ImageNote && o instanceof  TextNote)
            return 1;
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return date.toString() + "\t" + title;
    }
}
