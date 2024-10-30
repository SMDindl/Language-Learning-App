package com.languageLearner.game;

import java.util.HashMap;
import java.util.UUID;

public class DataConstants {

    private static final HashMap<String, UUID> LANGUAGE_UUIDS = new HashMap<>();

    public static final String FILIPINO = "filipino";
    public static final String FILIPINO_UUID_STRING = "1bafb0ae-3462-4ec3-9cc2-a98ff2898e72";
    public static final UUID FILIPINO_UUID = UUID.fromString(FILIPINO_UUID_STRING);

    // Link Language LANG/UUID with HashMap
    static {
        LANGUAGE_UUIDS.put(FILIPINO, FILIPINO_UUID);
    }

    public UUID getFilipinoLanguageUUID() {
        return FILIPINO_UUID;
    }

    // Idenifiers
    // public static final String LANG = "LANG";
    // public static final String GAME = "game";       // game is searched for by DataLoader to know when 
    // public static final String COMMENT = "comment"; // comments are ignored by dataLoader, comments can specifiy the name of the game for each searching

    // Games for filipino
    // public static final String FILIPINO_COLORS_GAME_EASY = "filipino_colors_game_easy";
    // public static final String FILIPINO_COLORS_GAME_MEDIUM = "filipino_colors_game_medium";
    // public static final String FILIPINO_COLORS_GAME_HARD = "filipino_colors_game_hard";

    // public static final String NUMBERS_GAME = "numbers_game";


    
}
