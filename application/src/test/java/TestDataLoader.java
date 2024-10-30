
import com.languageLearner.game.DataLoader;
import com.languageLearner.game.GameData;

public class TestDataLoader {

    public static void main(String[] args) {
        DataLoader.loadGames("json\\gamesData.json");
        System.out.println(GameData.getInstance().getGamesWithGameIDs());
    }
    
}
