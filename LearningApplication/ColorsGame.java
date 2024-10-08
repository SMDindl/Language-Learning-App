public class ColorsGame {
    private GameData gameData;

    public void startGame() {

    }

    public void teachWords() {

    }

    public Question askQuestion() {
        return null;
    }

    public boolean validateAnswer(String answer) {
        // return answer == Question.getCorrectAnswer();
        return false;
    }

    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }
}
