import java.util.ArrayList;

public class Question {
    // Attributes
    private String questionText;
    private int correctAnswerIndex;
    private ArrayList<String> answers;

    // Methods
    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        return answers.get(correctAnswerIndex);
    }
}
