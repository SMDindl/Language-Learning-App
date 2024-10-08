
/**
 * Tester
 */
public class Main extends DataConstants {

    public static void main(String args[]) {


        // load data
        DataLoader dataLoader = new DataLoader();
        GameData gameData = GameData.getInstance();
        dataLoader.loadGameData();

        // Set GameKey
        String language = DataConstants.FILIPINO;
        String difficulty = DataConstants.EASY;
        String gameType = DataConstants.COLORS_GAME;
        DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);
        
        // Test dataKey with loaded data
        gameType = DataConstants.ALPHABET_GAME;
        dataKey.setGameType(gameType);
        System.out.println("KEY: " + dataKey.toString());
        System.out.println(gameData.getWords(dataKey)  + "\n\n\n");

        gameType = DataConstants.COLORS_GAME;
        dataKey.setGameType(gameType);
        System.out.println("KEY: " + dataKey.toString());

        System.out.println(gameData.getWords(dataKey)  + "\n\n\n");

        difficulty = DataConstants.MEDIUM;
        dataKey.setDifficulty(difficulty);
        System.out.println("KEY: " + dataKey.toString());

        System.out.println(gameData.getWords(dataKey)  + "\n\n\n");

        difficulty = DataConstants.HARD;
        dataKey.setDifficulty(difficulty);
        System.out.println("KEY: " + dataKey.toString());

        System.out.println(gameData.getWords(dataKey)  + "\n\n\n");



    }

}