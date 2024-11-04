
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import java.util.UUID;

// public class QuestionFactoryTest {
    
//     private QuestionFactory questionFactory;

//     @BeforeEach
//     void setUp() {
//         questionFactory = new QuestionFactory();
//     }

//     @Test
//     void testCreateFITBQuestionReturnsCorrectInstance() {
//         UUID uuid = UUID.randomUUID();
//         Question question = questionFactory.createQuestion(uuid, QuestionType.FITB);
//         assertTrue(question instanceof FITBQuestion, 
//             "Expected createQuestion to return an instance of FITBQuestion.");
//     }

//     @Test
//     void testCreateMultipleChoiceQuestionReturnsCorrectInstance() {
//         UUID uuid = UUID.randomUUID();
//         String[] options = {"A", "B", "C", "D"};
//         Question question = questionFactory.createQuestion(uuid, QuestionType.MULTIPLE_CHOICE, "What is 2 + 2?", options, "C");
//         assertTrue(question instanceof MultipleChoiceQuestion, 
//             "Expected createQuestion to return an instance of MultipleChoiceQuestion.");
//     }
// }
