public class ColorsGame {
    private GameData gameData;

    public void startGame() {

    }

    public void teachWords() {

    }

    public Question askQuestion() {

    }

    public boolean validateAnswer(String answer) {
        return answer == Question.getCorrectAnswer();
    }

    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}
