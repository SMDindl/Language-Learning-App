
/**
 * 
 */
public class Word {
    // Attributes
    private String wordText;
    private String wordTranslation;
    private String exampleSentence;
    private String sentenceTranslation;

    // Constructor
    public Word(String wordText, String wordTranslation, String exampleSentence, String sentenceTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = exampleSentence;
        this.sentenceTranslation = sentenceTranslation;
    }

    public Word(String wordText, String wordTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
        this.exampleSentence = null;
        this.sentenceTranslation = null;
    }

    // Methods
    public String getWordText() {
        return wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public String getExampleSentence() {
        return exampleSentence; 
    }

    public String getSentenceTranslation() {
        return sentenceTranslation; 
    }

}
