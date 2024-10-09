import java.util.ArrayList;
import java.util.List;

public class Story {
    // Attributes
    private String title;
    private List<Page> pages;
    private String author;
    private int position;
    private ArrayList<Word> teachWords;

    // Constructor
    public Story(String title, List<Page> pages, String author, ArrayList<Word> teachWords) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.teachWords = teachWords;
        this.position = 0;
    }

    // Methods
    public String getTitle() {
        return title; // Placeholder
    }

    public List<Page> getPages() {
        return pages; // Placeholder
    }

    public String getAuthor() {
        return author; // Placeholder
    }

    public int getPosition() {
        return position; // Placeholder
    }

    public ArrayList<Word> getTeachWords() {
        return teachWords;
    }
}
