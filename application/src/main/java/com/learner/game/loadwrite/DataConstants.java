package com.learner.game.loadwrite;

/**
 * 
 */
public class DataConstants {

    // Constants for GameData.json

    // MAIN JSON ARRAY HOLDING EVERYTHING
    public static final String LANGUAGES = "LANGUAGES";

    // MAIN JSON OBJS
    public static final String LANG = "LANG";
    public static final String UUID = "UUID";

    // INFO OBJ + ITS OBJS/ARRAYS
    public static final String INFO = "INFO";
    public static final String DESCRIPTION = "description";
    public static final String OBJECTIVE = "objective";
    public static final String INSTRUCTIONS = "instructions"; // JSON ARRAY (holds strings split by commas)
    public static final String PREP = "prep";                 // JSON ARRAY (holds 3 json objects)

    // JSON OBJS WITHIN PREP OBJECT
    public static final String INTRO_CONCEPT = "introConcept"; 
    public static final String EXAMPLE_USAGE = "exampleUsage";
    public static final String GAME_TIP = "gameTip";

    // JSON ARRAYS 
    public static final String GAMES = "GAMES";
    public static final String QUESTIONS = "QUESTIONS";
    public static final String TEXT = "TEXT";

    // JSON OBJS WITHIN TEXT ARRAY (additonally a uuid is held within
    public static final String TEXT_OBJ = "text";
    public static final String ENGLISH_TEXT = "englishText";
    public static final String LINKED_TEXT = "linkedText";
    public static final String ENGLISH_LINKED_TEXT = "englishLinkedText";
    public static final String HELPER_TEXT = "helperText";

    // JSON OBJS WITHIN QUESTION
    public static final String QUESTION_TEXT = "questionText"; // JSON OBJ
    public static final String CHOICES = "choices";            // JSON ARRAY (postion 0 should always be the correct answer)

    // Constants for User.json
    
    
}
