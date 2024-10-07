
/**
 * Tester
 */
public class Main extends DataConstants {

    public static void main(String args[]) {

        String language = DataConstants.FILIPINO;
        String difficulty = DataConstants.EASY;
        String gameType = DataConstants.COLORS_GAME;

        DataLoader dataLoader = new DataLoader();
        GameData gameData = GameData.getInstance();
        DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);
        dataLoader.loadGameData();

        // Test dataKey
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