package base;

public class TextNote extends Note{

    private String content;

    public TextNote(String title) {
        super(title);
        this.content = "";
    }

    public TextNote(String title, String content) {
        super(title);
        this.content = content;
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
}
