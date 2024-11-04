// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;
// import org.junit.Test;
// import java.util.UUID;

// public class MatchingQuestionTest {

//     @Test
//     public void testGenerateQuestionCreatesCorrectQuestionText() {
//         // Arrange: Create an instance of MatchingQuestion with appropriate setup
//         UUID uuid = UUID.randomUUID(); // Example UUID
//         MatchingQuestion matchingQuestion = new MatchingQuestion(uuid);
        
//         // Act: Call the method to generate the question
//         matchingQuestion.generateQuestion();
        
//         // Assert: Check that the question text is as expected
//         String expectedText = "Your expected question text here"; // Replace with expected value
//         assertEquals(expectedText, matchingQuestion.getQuestionText());
//     }

//     @Test
//     public void testValidateAnswerCorrect() {
//         // Arrange: Setup a matching question and provide a correct answer
//         UUID uuid = UUID.randomUUID();
//         MatchingQuestion matchingQuestion = new MatchingQuestion(uuid);
//         matchingQuestion.setCorrectAnswer("Correct answer"); // Assuming a setter exists
        
//         // Act: Validate the correct answer
//         boolean isValid = matchingQuestion.validateAnswer("Correct answer");
        
//         // Assert: Expect true
//         assertTrue(isValid);
//     }

//     @Test
//     public void testValidateAnswerIncorrect() {
//         // Arrange: Setup a matching question
//         UUID uuid = UUID.randomUUID();
//         MatchingQuestion matchingQuestion = new MatchingQuestion(uuid);
//         matchingQuestion.setCorrectAnswer("Correct answer");
        
//         // Act: Validate an incorrect answer
//         boolean isValid = matchingQuestion.validateAnswer("Incorrect answer");
        
//         // Assert: Expect false
//         assertFalse(isValid);
//     }

//     // Add more tests as needed for other functionalities of MatchingQuestion
// }
