package base;

import javax.swing.Icon;
import java.io.*;

public class TextNote extends Note implements java.io.Serializable, Iconifiable {

    private String content;

    public TextNote(String title) {
        super(title);
        this.content = "";
    }

    public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }

    public TextNote (TextNote note) {
        super(note);
        this.content = note.content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String contentShort = "";
        if (content != null && !content.isEmpty() && content.contains(".")) {
            if (content.indexOf('.') + 1 <= 30) {
                contentShort = content.substring(0, content.indexOf('.')).trim();
            } else {
                contentShort = content.substring(0, 30);
            }
        }
        return "TextNote:" + getDate().toString() + "\t" + getTitle() + "\t" + contentShort;
    }

    public TextNote(File f) {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }

    private String getTextFromFile(String absolutePath) {
        String result = "";
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(absolutePath))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
            result = contentBuilder.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + absolutePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + absolutePath);
        }

        return result;
    }

    public void exportTextToFile(String pathFolder) {
        String fileName = this.getTitle().replace(" ", "_") + ".txt";
        File file = new File(pathFolder, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(this.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void iconify() {
        char firstCharacter = content.charAt(0);
        if ('a' <= firstCharacter && firstCharacter <= 'z') {
            content = new IconLowerCase(firstCharacter).base + content.substring(1);
        } else if ('A' <= firstCharacter && firstCharacter <= 'Z') {
            content = new IconUpperCase(firstCharacter).base + content.substring(1);
        } else if ('0' <= firstCharacter && firstCharacter <= '9') {
            content = new IconDigit(firstCharacter).base + content.substring(1);
        }
    }
}
