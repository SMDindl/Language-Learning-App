import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoriesGame {
    private GameData gameData;

    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhich story would you like to use?: \n");
        DataKey dataKey = null; //Temporary variable just to remove error in following line
        List<Story> storyList = gameData.getStories(dataKey);
        for(int i = 0; i < storyList.size(); i++) {
            //Prints out all the stories
            System.out.println((i+1) + ". " + storyList.get(i).getTitle());
        }
        int selection = keyboard.nextInt() - 1;
        teachWords(storyList.get(selection));
    }

    public void teachWords(Story story) {
        ArrayList<Word> newWords = story.getTeachWords();
    }

    public Question askQuestion() {
        return null;
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
