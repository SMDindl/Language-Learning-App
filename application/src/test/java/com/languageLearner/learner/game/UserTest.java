package com.languageLearner.learner.game;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.learner.game.Game;
import com.learner.game.GameManager;
import com.learner.game.User;
import com.learner.game.User.ProgressTracker;
import com.learner.game.UserList;
import com.learner.game.loadwrite.DataConstants;
import com.learner.game.loadwrite.DataLoader;
import com.learner.game.questions.MultipleChoiceQuestion;
import com.learner.game.questions.Question;

public class UserTest {
    @Test
    public void testAddUser() {
        UserList userList = UserList.getInstance();
        User newUser = new User("testman@email.sc.edu", "Testman", "Testman", "testpass", UUID.randomUUID());
        assertTrue(userList.addUser(newUser));
    }

    @Test
    public void testEmailTaken() {
        UserList userList = UserList.getInstance();
        assertFalse(userList.isEmailTaken("testuser@email.sc.edu"));
    }

    @Test
    public void testUsernameTaken() {
        UserList userList = UserList.getInstance();
        assertFalse(userList.isUsernameTaken("testusername"));
    }

    @Test
    public void testLogin() {
        UserList userList = UserList.getInstance();
        User newUser = new User("testman@email.sc.edu", "Testman", "Testman", "testpass", UUID.randomUUID());
        userList.addUser(newUser);
        assertTrue(newUser == userList.login("testman@email.sc.edu", "testpass"));
    }
    
    @Test
    public void testAddProgressTracker() {
        User newUser = new User("a", "", "", "test", UUID.randomUUID());
        UUID languageUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        User.ProgressTracker tracker = newUser.new ProgressTracker(languageUUID, "filipino");
        newUser.addProgressTracker(tracker);

        assertNotNull(newUser.getProgressTracker(languageUUID));
    }

    @Test
    public void testAddMissedQuestion() {
        User newUser = new User("a", "", "", "test", UUID.randomUUID());
        UUID languageUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        User.ProgressTracker tracker = newUser.new ProgressTracker(languageUUID, "filipino");
        newUser.addProgressTracker(tracker);

        UUID questionUUID = UUID.randomUUID();
        ArrayList<String> newOptionsList = new ArrayList<String>();
        newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a");
        Question newQuestion = new MultipleChoiceQuestion(questionUUID, UUID.randomUUID(), UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), "", newOptionsList);
        newUser.addMissedQuestion(newQuestion);

        assertTrue(tracker.getMissedQuestions().contains(newQuestion));
    }

    @Test
    public void testRemoveMissedQuestion() {
        User newUser = new User("a", "", "", "test", UUID.randomUUID());
        UUID languageUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        User.ProgressTracker tracker = newUser.new ProgressTracker(languageUUID, "filipino");
        newUser.addProgressTracker(tracker);

        UUID questionUUID = UUID.randomUUID();
        ArrayList<String> newOptionsList = new ArrayList<String>();
        newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a"); newOptionsList.add("a");
        Question newQuestion = new MultipleChoiceQuestion(questionUUID, UUID.randomUUID(), UUID.fromString("1bafb0ae-3462-4ec3-9cc2-a98ff2898e72"), "", newOptionsList);
        newUser.addMissedQuestion(newQuestion);
        newUser.removeMissedQuestion(newQuestion);

        assertFalse(tracker.getMissedQuestions().contains(newQuestion));
    }

    @Test
    public void testAddCompletedGame() {
        DataLoader.loadGameData(DataConstants.GAME_DATA_FILE_JUNIT);
        GameManager gameManager = GameManager.getInstance();
        Game colorsGame = gameManager.findGameByUUID(UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5"));

        User newUser = new User("a", "", "", "test", UUID.randomUUID());
        UUID languageUUID = UUID.fromString("8ce4fefc-a539-4546-9d7e-0ac8778f8de5");
        User.ProgressTracker tracker = newUser.new ProgressTracker(languageUUID, "filipino");
        newUser.addProgressTracker(tracker);

        tracker.addCompletedGame(colorsGame.getUUID());

        assertNotNull(tracker.getCompletedGames());
    }

    
    
}
