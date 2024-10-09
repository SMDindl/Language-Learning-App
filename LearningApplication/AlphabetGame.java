public class AlphabetGame {
    private GameData gameData;

    public AlphabetGame() {
        this.gameData = GameData.getInstance();
    }

    public void startGame() {

    }

    public void teachWords() {

    }

    public Question askQuestion(int i) {
        Question newQuestion = new Question;
        newQuestion = this.gameData.getQuestionText(i);
        return newQuestion;
    }

    public boolean validateAnswer(String answer, Question question) {
       return answer == question.getCorrectAnswer();
    }

    public void provideFeedback(boolean isCorrect) {
        if(isCorrect) 
            System.out.println("Well done!");
        else
            System.out.println("Better luck next time");
    }

}