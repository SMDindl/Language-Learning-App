package com.languageLearner.app;

import java.util.ArrayList;

import com.languageLearner.data.ProgressTracker;
import com.languageLearner.data.Question;
import com.languageLearner.data.User;

public class ProgressViewer {

    // Displays the user's progress for the specified language
    public static void displayProgress(User user, String language) {
        ProgressTracker tracker = user.getProgressTrackerByLanguage(language);
        System.out.println("\n=== Progress Overview for " + user.getDisplayName() + " ===");
        System.out.println("Language: " + language);
        System.out.println("Completed games: " + tracker.getCompletedGames().size());
        System.out.println("Missed questions: " + tracker.getMissedQuestions().size());
        System.out.println();
    }

    // Reviews the user's missed questions for the specified language
    public static void reviewMissedQuestions(User user, String language) {
        ProgressTracker tracker = user.getProgressTrackerByLanguage(language);
        if (tracker.getMissedQuestions().isEmpty()) {
            System.out.println("No missed questions to review for language: " + language);
        } else {
            System.out.println("=== Reviewing Missed Questions for Language: " + language + " ===");
            ArrayList<Question> missedAnswerList = new ArrayList<>();
            for (int i = 0; i < tracker.getMissedQuestions().size(); i++) {
                Question question = tracker.getMissedQuestions().get(i);
                if(question.askMissedQuestion()) {
                    missedAnswerList.add(question);
                }
                // Additional logic for validating and providing feedback could go here
            }
            for(int i = 0; i < missedAnswerList.size(); i++) {
                tracker.removeMissedQuestion(missedAnswerList.get(i));
            }
        }
    }

    // Simulate a method to get user answer for testing purposes
    private static Object getSimulatedUserAnswer(String questionType) {
        switch (questionType) {
            case "multiple_choice":
                return 0; // Simulate selecting the first option
            case "true_false":
                return true; // Simulate answering "true"
            case "FITB":
                return "sampleAnswer"; // Sample FITB answer
            case "matching":
                return "1-A,2-B"; // Example matching answer
            default:
                return null;
        }
    }
}
