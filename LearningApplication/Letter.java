import java.util.List;

public class Letter {
    // Attributes
    private String letter;
    private String pronunciation;
    private List<String> exampleWords;

    // Constructor
    public Letter(String letter, String pronunciation, List<String> exampleWords) {
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

    public List<String> getExampleWords() {
        return exampleWords;
    }
}
