
import java.util.ArrayList;

import com.languageLearner.game.DataLoader;
import com.languageLearner.game.Game;
import com.languageLearner.game.GameData;

public class TestDataLoader {

    public static void main(String[] args) {
        DataLoader.loadGames();
        GameData gameData = GameData.getInstance();
        ArrayList<Game> games = gameData.getGames();
        for(int i = 0; i < games.size(); i++) {
            System.out.println(games.get(i));
        }
    }
    
}
