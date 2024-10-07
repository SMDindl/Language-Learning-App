public class GameKey {
    private static GameKey instance;
    private String language;
    private String difficulty;
    private String gameType;

    public static GameKey getInstance() {
        if (instance == null) {
            instance = new GameKey();
        }
        return instance;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getGameType() {
        return gameType;
    }
}
