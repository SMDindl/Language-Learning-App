import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoriesGame {
    private GameData gameData;

    public void startGame() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What would you like to do?: \n1. Take a story quiz \n2. Return to game selection");
        int prompt = keyboard.nextInt();
        if (prompt == 2) {
            //Implement this to go back to the game selection
        }

        //User picks the story to work on
        Story selection = pickStory();
        teachWords(selection);
        List<Page> storyPages = selection.getPages();
        for(int i = 0; i < storyPages.size(); i++) {
            storyPages.get(i).getContent();
        }
        System.out.println("When you're ready for the quiz, hit the ENTER key.");
        keyboard.next();

        //Questions for after the user is done with the story
        Question newQuestion = askQuestion(selection);
        if(validateAnswer(keyboard.nextLine(), newQuestion) == true)
            System.out.println("well done!");
        else
            System.out.println("Better luck next time!");
    }

    public Story pickStory() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWhich story would you like to use?: \n");
        DataKey dataKey = null; //Temporary variable just to remove error in following line
        List<Story> storyList = gameData.getStories(dataKey);
        for(int i = 0; i < storyList.size(); i++) {
            //Prints out all the stories
            System.out.println((i+1) + ". " + storyList.get(i).getTitle());
        }
        Story selection = storyList.get(keyboard.nextInt() - 1);
        return selection;
    }

    public void teachWords(Story story) {
        ArrayList<Word> newWords = story.getTeachWords();
        for(int i = 0; i < newWords.size(); i++) {
            //Might be better to store it as a different list, one that has the word and then the information being taught
            //For now, just storing it as a list of Word to print out the word and then the translation
            System.out.println(newWords.get(i).getWordText());
            System.out.println(newWords.get(i).getWordTranslation());
        }
    }

    public Question askQuestion(Story selection) {
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
