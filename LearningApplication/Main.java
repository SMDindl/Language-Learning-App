
/**
 * Tester
 */
public class Main extends DataConstants {

    public static void main(String args[]) {

        // Test dataKey
        String language = DataConstants.FILIPINO;
        String difficulty = DataConstants.EASY;
        String gameType = DataConstants.COLORS_GAME;

        DataKey dataKey = DataKey.getInstance(language, gameType, difficulty);
        System.out.println(dataKey.toString());

        gameType = DataConstants.ALPHABET_GAME;
        dataKey.setGameType(gameType);
        System.out.println(dataKey.toString());

    }

}