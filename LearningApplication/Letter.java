import java.util.ArrayList;

public class Letter {
    // Attributes
    private String letter;
    private String pronunciation;
    private ArrayList<String> exampleWords;

    // Constructor
    public Letter(String letter, String pronunciation, ArrayList<String> exampleWords) {
        this.letter = letter;
        this.pronunciation = pronunciation;
        this.exampleWords = exampleWords;
    }

    // Methods
    public String getLetter() {
        return letter;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public ArrayList<String> getExampleWords() {
        return exampleWords;
    }
}
